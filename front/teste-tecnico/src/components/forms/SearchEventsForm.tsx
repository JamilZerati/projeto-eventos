import {Input} from "@/components/ui/input"
import {SubmitHandler, useForm} from "react-hook-form";
import {useEffect, useState} from "react";
import {Checkbox} from "@/components/ui/checkbox.tsx";
import {Calendar} from "@/components/ui/calendar.tsx";
import {Button} from "@/components/ui/button.tsx";
import {
    Form,
    FormControl,
    FormField,
    FormItem,
    FormLabel,
    FormMessage
} from "@/components/ui/form.tsx";
import {
    Select,
    SelectContent,
    SelectItem,
    SelectTrigger,
    SelectValue,
} from "@/components/ui/select"
import {zodResolver} from "@hookform/resolvers/zod";
import {CalendarIcon} from "@radix-ui/react-icons";
import {Popover, PopoverContent, PopoverTrigger} from "@/components/ui/popover.tsx";
import {format} from "date-fns";
import {EventoService} from "@/api/EventoService.ts";
import {Evento, SearchEventsSchema, SearchEventsSchemaInfer} from "@/api/model/Evento.ts";
import {InstituicaoService} from "@/api/InstituicaoService.ts";
import {Instituicao} from "@/api/model/Instituicao.ts";
import {formatDate} from "@/api/util/date.ts";


export function SearchEventsForm(props: { onClickBuscaEventos: (data: Evento[]) => void }) {

    const [instituicoes, setInstituicoes] = useState<Instituicao[]>([]);
    const [message, setMessage] = useState<string | null>(null);
    const [messageType, setMessageType] = useState<'success' | 'error' | null>(null);

    useEffect(() => {
        InstituicaoService.fetchInstituicoes().then((response) => {
            setInstituicoes(response);

        }).catch((error : Error) => {
            setMessage("Erro ao buscar instituições. "+ error.message);
            setMessageType("error");
        });
        return () => {
            setMessage(null);
            setMessageType(null);
        }
    }, []);

    const form = useForm<SearchEventsSchemaInfer>({
        resolver: zodResolver(SearchEventsSchema)
    });


    const handleSearchEvents = (data: SearchEventsSchemaInfer) => {
        EventoService.fetchEventos(data).then((response) => {
            response.length === 0 ? setMessage("Nenhum evento encontrado") : setMessage("Eventos buscados com sucesso");
            setMessageType("success");
            props.onClickBuscaEventos(response);
        }).catch((error : Error) => {
            setMessage("Erro ao buscar eventos. "+ error.message);
            setMessageType("error");
        });
    }

    const onSubmit: SubmitHandler<SearchEventsSchemaInfer> = (data) => {
        if (data.dataFinal) {
            data.dataFinal =formatDate(data.dataFinal).toString();
        }
        if (data.dataInicial){
            data.dataInicial = formatDate(data.dataInicial).toString();
        }
        handleSearchEvents(data)
    }

    return (
            <Form {...form}>
                <form onSubmit={form.handleSubmit(onSubmit)}  className={"flex-col w-1/2 mx-auto"}>
                    { message && (
                        <div className={`message ${messageType}`}>
                            {message}
                        </div>
                    )}
                    <FormField
                        control={form.control}
                        name="instituicao"
                        render={({field}) => (
                            <FormItem>
                                <FormLabel>Instituição</FormLabel>
                                <Select
                                    onValueChange={(value) => {
                                        const selectedInstituicao = instituicoes.find(inst => inst.nome === value);
                                        field.onChange(selectedInstituicao);
                                    }}
                                    defaultValue={field.value?.nome}
                                >
                                    <FormControl>
                                        <SelectTrigger>
                                            <SelectValue placeholder="Selecione uma instituição"/>
                                        </SelectTrigger>
                                    </FormControl>
                                    <SelectContent>
                                        {instituicoes.map((instituicao) => (
                                            <SelectItem key={instituicao.id} value={instituicao.nome}>
                                                {instituicao.nome}
                                            </SelectItem>
                                        ))}
                                    </SelectContent>
                                </Select>
                                <FormMessage/>
                            </FormItem>
                        )}
                    />

                    <FormField control={form.control} name={"nome"}
                               render={({field}) => (
                                   <FormItem className={"mt-2"}>
                                       <FormLabel>Nome do Evento</FormLabel>
                                       <FormControl>
                                           <Input placeholder="Nome do evento" {...field}/>
                                       </FormControl>
                                       <FormMessage/>
                                   </FormItem>
                               )}
                    />

                    <FormField control={form.control} name={"dataInicial"}
                               render={({field}) => (
                                   <FormItem className="flex flex-col mt-3">
                                       <FormLabel>Data Inicial do evento</FormLabel>
                                       <Popover>
                                           <PopoverTrigger asChild>
                                               <FormControl>
                                                   <Button variant={"outline"}>
                                                       {field.value ? (
                                                           format(field.value, "PPP")
                                                       ) : (
                                                           <span>Pick a date</span>
                                                       )}
                                                       <CalendarIcon className="ml-auto h-4 w-4 opacity-50"/>
                                                   </Button>
                                               </FormControl>
                                           </PopoverTrigger>
                                           <PopoverContent className="w-auto p-0" align="start">
                                               <Calendar
                                                   mode="single"
                                                   selected={field.value}
                                                   onSelect={field.onChange}
                                                   disabled={(date) => date < new Date("1900-01-01")
                                                   }
                                                   initialFocus
                                               />
                                           </PopoverContent>
                                       </Popover>
                                       <FormMessage/>
                                   </FormItem>
                               )}
                    />

                    <FormField control={form.control} name={"dataFinal"}
                               render={({field}) => (
                                   <FormItem className="flex flex-col mt-3">
                                       <FormLabel>Data Final</FormLabel>
                                       <Popover>
                                           <PopoverTrigger asChild>
                                               <FormControl>
                                                   <Button variant={"outline"}>
                                                       {field.value ? (
                                                           format(field.value, "PPP")
                                                       ) : (
                                                           <span>Pick a date</span>
                                                       )}
                                                       <CalendarIcon className="ml-auto h-4 w-4 opacity-50"/>
                                                   </Button>
                                               </FormControl>
                                           </PopoverTrigger>
                                           <PopoverContent className="w-auto p-0" align="start">
                                               <Calendar
                                                   mode="single"
                                                   selected={field.value}
                                                   onSelect={field.onChange}
                                                   disabled={(date) =>
                                                      date < new Date("1900-01-01")
                                                   }
                                                   initialFocus
                                               />
                                           </PopoverContent>
                                       </Popover>
                                       <FormMessage/>
                                   </FormItem>
                               )}
                    />

                    <FormField control={form.control} name={"ativo"}
                               render={({field}) => (
                                   <FormItem className={"mt-2"}>
                                       <FormLabel>Evento Ativo</FormLabel>
                                       <FormControl>
                                           <Checkbox checked={field.value} onCheckedChange={field.onChange}
                                                     className={"ml-2"}/>
                                       </FormControl>
                                       <FormMessage/>
                                   </FormItem>
                               )}
                    />

                    <Button type="submit">Buscar Eventos</Button>
                </form>
            </Form>
    )
}