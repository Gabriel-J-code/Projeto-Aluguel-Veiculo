import { useRouter } from "next/router"
import { FormEvent, useState } from "react"

interface Cliente {
    cnpjLoja : string,
    nome : string,
    cpf : string,
    email : string,
    telefone : string
}

import styles from "./cliente.module.scss"

const Cliente = () => {
    const router = useRouter()
    const cnpj = new Buffer(`${router.query.cnpj}`, "base64").toString("ascii")
    const [nome, setNome] = useState<string>("")
    const [cpf, setCpf] = useState<string>("")
    const [email, setEmail] = useState<string>("")
    const [telefone, setTelefone] = useState<string>("")


    async function handleSubmit(event : FormEvent) {
        event.preventDefault()
        
        const data : Cliente = {
            cnpjLoja : cnpj,
            nome : nome,
            cpf : cpf,
            email : email,
            telefone : telefone   
        }
        
        const options = {
            method : "POST",
            headers : {
                "Content-Type" : "application/json",
                "Access-Control-Allow-Headers": "X-Custom-Header"
            },
            body : JSON.stringify(data)
        }
        console.log(options.body)
        
        const res = await fetch(`${process.env.BASE_URL}/loja/clientes/cadastrar`, options)
        if (res.status == 400) {
            alert("Ainda tem campos a serem preenchidos!")
            return
        }

        alert("ok!")
        router.back() 
    }
    
    return (
        <div className={styles.page}>
            <div className ={styles.overlay}>
                <h1>Novo Cliente</h1>


                <form 
                    onSubmit = {handleSubmit}
                    autoComplete = "off"
                >
                    <fieldset>
                        <div className={styles.inputs}>
                            <label htmlFor= "nome">Nome:</label>
                            <input
                                placeholder = "Nome do cliente" 
                                id="nome"
                                value={nome}
                                onChange= {(e) => setNome(e.target.value)}
                            />
                        </div>

                        <div className={styles.inputs}>
                            <label htmlFor= "cpf">CPF:</label>
                            <input 
                                placeholder = "000.000.000-00" 
                                id="cpf"
                                value={cpf}
                                onChange= {(e) => setCpf(e.target.value)}
                            />
                        </div>

                        <div className={styles.inputs}>
                            <label htmlFor= "email">Email:</label>
                            <input 
                                placeholder = "email@site.com" 
                                id="email"
                                value={email}
                                onChange= {(e) => setEmail(e.target.value)}
                            />
                        </div>

                        <div className={styles.inputs}>
                            <label htmlFor= "telefone">Telefone:</label>
                            <input 
                                placeholder = "(00)00000-0000"
                                id="telefone"
                                value={telefone}
                                onChange= {(e) => setTelefone(e.target.value)}
                            />
                        </div>
                        <button type="submit">
                            Criar
                        </button>
                    </fieldset>
                </form>
            </div>
        </div>
    )
}

export default Cliente