import {Evento, SearchEventsSchemaInfer} from "@/api/model/Evento.ts";

export class EventoService {

    static async fetchEventos(data: SearchEventsSchemaInfer): Promise<Evento[]> {
        const params = new URLSearchParams();

        if (data.nome) params.append("nome", data.nome);
        if (data.dataInicial) params.append("dataInicial", data.dataInicial.toString());
        if (data.dataFinal) params.append("dataFinal", data.dataFinal.toString());
        if (data.instituicao) params.append("instituicaoId", String(data.instituicao.id));
        if (data.ativo) params.append("ativo", String(data.ativo));

        const response = await fetch(`http://localhost:8080/eventos?${params.toString()}`, {
            method: "GET",
            headers: {"Content-Type": "application/json"}
        });

        if (!response.ok) {
            throw new Error("Erro ao buscar eventos");
        }

        return response.json();
    }


    static async createEvento(evento: Evento) {
        return await fetch("http://localhost:8080/eventos", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(evento)
        }).then((response) => {
            if (!response.ok) {
                throw new Error("Erro ao criar evento");
            }
            return true;
        });
    }

    static async editarEvento(evento: Evento, eventoId: number) {
        return await fetch(`http://localhost:8080/eventos/${eventoId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(evento)
        }).then((response) => {
            if (!response.ok) {
                throw new Error("Erro ao editar evento");
            }
            return true;
        });
    }

    static async deleteEvento(eventoId: number) {
        return await fetch(`http://localhost:8080/eventos/${eventoId}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json"
            }
        }).then((response) => {
            if (!response.ok) {
                throw new Error("Erro ao deletar evento");
            }
            return true;
        });
    }
}