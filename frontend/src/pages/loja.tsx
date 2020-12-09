
import { useRouter } from "next/router"
import { FormEvent, useState } from "react"
import BASE_URL from "../components/const.url"
import styles from './loja.module.scss'

const CriarLoja = () => {
    const router = useRouter()

    const [nome, setNome] =  useState<string>("")
    const [cnpj, setCnpj] =  useState<string>("")
    const [ie, setIe] =  useState<string>("")
    const [cep, setCep] =  useState<string>("")
    const [numero, setNumero] =  useState<string>("")

    async function handleSubmit(event : FormEvent) {
        event.preventDefault()
        
        const data = {
            nome : nome,
            cnpj : cnpj,
            ie : ie,
            cep : cep,
            numero : numero
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
        
        const res = await fetch(`${BASE_URL.url}/loja`, options)
        if (res.status == 400) {
            alert("Ainda tem campos a serem preenchidos!")
            return
        }

        alert("ok!")
        router.push("/")
        
    }

    return (
        <div className={styles.page}>
            <div className ={styles.overlay}>
                <h1>Criar Loja</h1>


                <form 
                    onSubmit = {handleSubmit}
                    autoComplete = "off"
                >
                    <fieldset>
                        <div>
                            <label htmlFor= "nome">Nome:</label>
                            <input
                                placeholder = "Nome da loja" 
                                id="nome"
                                value={nome}
                                onChange= {(e) => setNome(e.target.value)}
                            />
                        </div>

                        <div>
                            <label htmlFor= "cnpj">Cnpj:</label>
                            <input 
                                placeholder = "CNPJ" 
                                id="cnpj"
                                value={cnpj}
                                onChange= {(e) => setCnpj(e.target.value)}
                            />
                        </div>

                        <div>
                            <label htmlFor= "ie">Inscrição estadual:</label>
                            <input 
                                placeholder = "IE" 
                                id="ie"
                                value={ie}
                                onChange= {(e) => setIe(e.target.value)}
                            />
                        </div>

                        <div>
                            <label htmlFor= "cep">Cep:</label>
                            <input 
                                placeholder = "00000-000"
                                id="name"
                                value={cep}
                                onChange= {(e) => setCep(e.target.value)}
                            />
                        </div>

                        <div>
                            <label htmlFor= "numero">N°:</label>
                            <input 
                                placeholder = "00"
                                id="numero"
                                value= {numero === "s/n" ? "" : numero}
                                onChange= {(e) => {
                                        if(e.target.value === "")
                                            setNumero("s/n")
                                        else 
                                            setNumero(e.target.value)
                                    }
                                }
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

export default CriarLoja