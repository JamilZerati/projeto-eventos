import {
    Dialog,
    DialogContent,
    DialogDescription,
    DialogHeader,
    DialogTitle, DialogTrigger,
} from "@/components/ui/dialog"
import {z} from "zod";
import {SubmitHandler, useForm} from "react-hook-form";
import React, {useEffect, useState} from "react";
import {
    Form,
} from "@/components/ui/form.tsx";
import {zodResolver} from "@hookform/resolvers/zod";
import {EventoService} from "@/api/EventoService.ts";
import {Evento} from "@/api/model/Evento.ts";
import {Instituicao} from "@/api/model/Instituicao.ts";
import {InstituicaoService} from "@/api/InstituicaoService.ts";
import {FormShared} from "@/components/forms/insert/FormShared.tsx";
import {Button} from "@/components/ui/button.tsx";

const createEventSchema = z.object({
    name: z.string().min(3),
    dataInicial: z.date(),
    dataFinal: z.date(),
    ativo: z.boolean(),
    instituicao: z.object({
        id: z.number(),
        nome: z.string(),
        tipo: z.string().optional()
    })
}).refine((data) => data.dataFinal > data.dataInicial, {
    message: "Data final deve ser maior que a data inicial",
    path: ["dataFinal"]
});

type CreateEventoSchema = z.infer<typeof createEventSchema>;

type FormInsercaoProps = {
    message: string | null;
    setMessage: (message: string) => void;
    setMessageType: (messageType: 'success' | 'error' | null) => void;
    messageType: 'success' | 'error' | null;
    handleDialogClose: () => void;
}

export function FormInsercao({message, setMessage, messageType, setMessageType,
                             handleDialogClose}: FormInsercaoProps) {

    const [instituicoes, setInstituicoes] = useState<Instituicao[]>([]);

    const form = useForm<CreateEventoSchema>({
        resolver: zodResolver(createEventSchema)
    });

    useEffect(() => {
        InstituicaoService.fetchInstituicoes().then((data) => setInstituicoes(data));
    }, []);

    async function handleCreateEvent({name, dataInicial, dataFinal, ativo, instituicao}: CreateEventoSchema) {
        const evento = new Evento(name, dataInicial, dataFinal, ativo, instituicao);
        await EventoService.createEvento(evento).then(() => {
            setMessage("Evento criado com sucesso!");
            setMessageType('success');
        })
            .catch(e => {
                setMessage(e.message);
                setMessageType('error')
            });
    }

    const onSubmit: SubmitHandler<CreateEventoSchema> = (data) => {
        handleCreateEvent(data)
    }

    return (
        <Dialog onOpenChange={handleDialogClose}>
            <DialogTrigger asChild>
                <Button>Adicionar Evento</Button>
            </DialogTrigger>
            <DialogContent className="sm:max-w-[425px]">
                <DialogHeader>
                    <DialogTitle>Adicionar Evento</DialogTitle>
                    <DialogDescription>
                        Adicione um novo evento ao calendário
                    </DialogDescription>
                </DialogHeader>
                <Form {...form}>
                    {message ? (
                        <div className={`message ${messageType}`}>
                            {message}
                        </div>
                    ) : (
                        <FormShared form={form} instituicoes={instituicoes} onSubmit={onSubmit}/>
                    )}
                </Form>
            </DialogContent>
        </Dialog>

    )
}