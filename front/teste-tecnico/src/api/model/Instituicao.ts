export class Instituicao
{
    id: number;
    nome: string;
    tipo?: string;

    constructor(id: number, nome: string, tipo: string)
    {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
    }
}