import {Button} from "@/components/ui/button.tsx";
import {Dialog, DialogTrigger} from "@/components/ui/dialog.tsx";
import {SearchEventsForm} from "@/components/forms/SearchEventsForm.tsx";
import {FormInsercao} from "@/components/forms/insert/FormInsercao.tsx";
import {Evento} from "@/api/model/Evento.ts";
import {useState} from "react";
import {EventList} from "@/components/event/EventList.tsx";
import {EventoService} from "@/api/EventoService.ts";
import {FormEdicao} from "@/components/forms/insert/FormEdicao.tsx";



export function Main() {

    const [eventos, setEventos] = useState<Evento[]>([]);
    const [message, setMessage] = useState<string | null>(null);
    const [messageType, setMessageType] = useState<'success' | 'error' | null>(null);

    function handleClickBuscaEventos(data: Evento[]){
        setEventos(data);
    }
    function handleDialogClose(){
        setMessage(null);
        setMessageType(null);
    }


    return (
            <main className={"h-full w-full"}>
                    <h1 className={"border-e-blue-950 text-gray-600 w-1/2 text-2xl mx-auto text-center flex justify-between"}>Gerente de Eventos
                    <FormInsercao message={message} setMessage={setMessage} messageType={messageType} setMessageType={setMessageType} handleCloseDialog={handleDialogClose}/>
                    </h1>
                    <SearchEventsForm onClickBuscaEventos={handleClickBuscaEventos}/>
                    <EventList events={eventos} ></EventList>

            </main>

    )
}