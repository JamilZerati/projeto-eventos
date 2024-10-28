import {Instituicao} from "@/api/model/Instituicao.ts";
import {formatDate} from "date-fns";
import {z} from "zod";

export class Evento {
    eventoId?: number;
    nome: string;
    dataInicial: string;
    dataFinal: string;
    ativo: boolean;
    instituicao: Instituicao;

    constructor(nome: string, dataInicial: Date, dataFinal: Date, ativo: boolean, instituicao: Instituicao) {
        this.nome = nome;
        this.dataInicial = formatDate(dataInicial, "yyyy-MM-dd");
        this.dataFinal = formatDate(dataFinal, "yyyy-MM-dd");
        this.ativo = ativo;
        this.instituicao = instituicao;
    }

}

export const SearchEventsSchema = z.object({
    nome: z.string().optional(),
    dataInicial: z.coerce.string().optional(),
    dataFinal: z.coerce.string().optional(),
    instituicao: z.object({
        id: z.number(),
        nome: z.string().optional(),
        tipo: z.string().optional(),
    }, {
        required_error: "Selecionar uma instituição é obrigatório."
    }),
    ativo: z.boolean().optional(),
});


export type SearchEventsSchemaInfer = z.infer<typeof SearchEventsSchema>;