import {Card, CardContent, CardDescription, CardHeader, CardTitle} from "@/components/ui/card.tsx";

interface EventProps {
    id: number;
    name: string;
    dataInicial: Date;
    dataFinal: Date;
    ativo: boolean;
    nomeInstituicao: string;
}

export function Event(props: EventProps) {
    return (
        <Card>
            <CardHeader>
                <CardTitle>Evento {props.id}</CardTitle>
                <CardDescription>{props.name}</CardDescription>
            </CardHeader>
            <CardContent>
                <p>Data Inicial: {props.dataInicial.toISOString()}</p>
                <p>Data Final: {props.dataFinal.toISOString()}</p>
                <p>Instituição: {props.nomeInstituicao}</p>
            </CardContent>
        </Card>
            )
}