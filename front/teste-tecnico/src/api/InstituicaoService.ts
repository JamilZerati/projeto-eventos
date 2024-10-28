import {Instituicao} from "@/api/model/Instituicao.ts";

function fetchInstituicoes(): Promise<Instituicao[]> {
    return fetch("http://localhost:8080/instituicoes", {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).then((response) => {
        console.log(response)
            if (!response.ok) {
                throw new Error("Erro ao buscar instituições");
            }
            return response.json();
        }
    );
}


export const InstituicaoService = {
    fetchInstituicoes
}