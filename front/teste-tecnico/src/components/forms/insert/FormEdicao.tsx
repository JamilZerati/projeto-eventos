import {
    Dialog,
    DialogClose,
    DialogContent,
    DialogDescription,
    DialogFooter,
    DialogHeader,
    DialogTitle, DialogTrigger,
} from "@/components/ui/dialog"
import {Input} from "@/components/ui/input"
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

const editEventSchema = z.object({
    eventoId: z.number(),
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

type EditEventSchema = z.infer<typeof editEventSchema>;

type FormInsercaoProps = {
    message: string | null;
    setMessage: (message: string) => void;
    setMessageType: (messageType: 'success' | 'error' | null) => void;
    messageType: 'success' | 'error' | null;
    eventoId: number;
    handleDialogClose: () => void;
}

export function FormEdicao({message, setMessage, messageType, setMessageType, eventoId,
                           handleDialogClose}: FormInsercaoProps) {

    const [instituicoes, setInstituicoes] = useState<Instituicao[]>([]);

    const form = useForm<EditEventSchema>({
        resolver: zodResolver(editEventSchema)
    });

    useEffect(() => {
        console.log('fetching edicao')
        InstituicaoService.fetchInstituicoes().then((data) => setInstituicoes(data));
    }, []);

    async function handleEditEvent({name, dataInicial, dataFinal, ativo, instituicao, eventoId}: EditEventSchema) {
        const evento = new Evento(name, dataInicial, dataFinal, ativo, instituicao);
        await EventoService.editarEvento(evento, eventoId).then(() => {
            setMessage("Evento editado com sucesso!");
            setMessageType('success');
        })
            .catch(e => {
                setMessage(e.message);
                setMessageType('error')
            });
    }

    const onSubmit: SubmitHandler<EditEventSchema> = (data) => {
        handleEditEvent(data, eventoId)
    }

    return (
        <Dialog onOpenChange={handleDialogClose}>
            <DialogContent className="sm:max-w-[425px]">
                <DialogHeader>
                    <DialogTitle>Editar Evento</DialogTitle>
                    <DialogDescription>
                        Edite um evento existente
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