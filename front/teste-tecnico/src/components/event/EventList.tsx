import {Evento} from "@/api/model/Evento.ts";
import {Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle} from "@/components/ui/card.tsx";
import {Button} from "@/components/ui/button.tsx";
import {EventoService} from "@/api/EventoService.ts";
import {FormEdicao} from "@/components/forms/insert/FormEdicao.tsx";
import {useState} from "react";
interface EventListProps {
    events: Evento[];
    message: string | null;
    setMessage: (message: string) => void;
    messageType: 'success' | 'error' | null;
    setMessageType: (messageType: 'success' | 'error' | null) => void;
    handleDialogClose: () => void;
    setEvents: (events: Evento[]) => void;

}

export function EventList({ events, message, setMessage, messageType, setMessageType,
                          handleDialogClose, setEvents}: EventListProps) {
    const [isEditing, setIsEditing] = useState(false);
    const [currentEvent, setCurrentEvent] = useState(null);

    function handleDeleteEvent(id) {
        EventoService.deleteEvento(id).then(() => {
            setMessage("Evento deletado com sucesso!");
            setMessageType('success');
            handleDialogClose();
        });
        setEvents(EventoService.fetchEventos(events[0]));

    }

    function handleEditar(evento : Evento) {
        //Todo
    }

    function closeEditForm() {
        setIsEditing(false);
        setCurrentEvent(null);
    }

    return (
        <div className={"flex flex-col items-center w-1/2 mx-auto p-2 m-2 gap-6"}>
            {isEditing && currentEvent && (
                <FormEdicao
                    handleDialogClose={handleDialogClose}
                    eventoId={currentEvent.eventoId}
                    evento={currentEvent}
                    closeForm={closeEditForm}
                    message={message}
                    setMessage={setMessage}
                    messageType={messageType}
                    setMessageType={setMessageType}
                />
            )}
            <div className={"flex justify-between w-full flex-wrap"}>
                {events.map((evento) => (
                    <Card key={evento.eventoId} className={"items-center max-w-72 rounded-xl"}>
                        <CardHeader>
                            <CardTitle>{evento.nome}</CardTitle>
                            <CardDescription>Instituição: {evento.instituicao.nome}</CardDescription>
                        </CardHeader>
                        <CardContent className={"flex flex-col gap-4"}>
                            <h2><b>Nome: </b>{evento.nome}</h2>
                            <p><b>Data Inicial: </b>{evento.dataInicial}</p>
                            <p><b>Data Final:</b> {evento.dataFinal}</p>
                        </CardContent>
                        <CardFooter className={"flex gap-4 justify-between"}>
                            <p><b>Status: </b>{evento.ativo ? 'Ativo' : 'Inativo'}</p>
                            <Button type={"button"} onClick={() => handleDeleteEvent(evento.eventoId)}>Deletar</Button>
                            <Button type={"button"} onClick={() => handleEditar(evento)}>Editar</Button>
                        </CardFooter>
                    </Card>
                ))}
            </div>
        </div>
    );
}