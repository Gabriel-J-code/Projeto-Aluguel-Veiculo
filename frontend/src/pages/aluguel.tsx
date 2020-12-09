
import { useRouter } from "next/router"
import { FormEvent, useEffect, useState } from "react"
import styles from './aluguel.module.scss'
import Select from 'react-select'
import BASE_URL from '../components/const.url';

interface Aluguel {
    codigoVeiculo : string,
    cnpjLoja : string
    cpfCliente : string, 
    horarioInicio : string, 
    horarioTermino : string
}

interface Selecao {
    key: string,
    label : string,
    value : string
}

const Aluguel = () => {
    
    const router = useRouter()

    const [veiculos, setVeiculos] = useState<Selecao[]>([])
    const [clientes, setClientes] = useState<Selecao[]>([])

    const [cpfCliente, setCpfCliente] = useState<string>("")
    const [codigoVeiculo, setCodigoVeiculo] = useState<string>("")

    const [dataI, setDataI] = useState<string>("")
    const [dataT, setDataT] = useState<string>("")
    
    const [horaI, setHoraI] = useState<string>("")
    const [horaT, setHoraT] = useState<string>("")
    const [cnpjLoja, setCnpjLoja] = useState<string>("")



    async function handleSubmit(event : FormEvent) {
        event.preventDefault()
        
        const data : Aluguel = {
            codigoVeiculo : codigoVeiculo,
            cnpjLoja : cnpjLoja,
            cpfCliente : cpfCliente, 
            horarioInicio : `${dataI} ${horaI}`, 
            horarioTermino : `${dataT} ${horaT}`
        }

        if(dataI.length != 10 || dataT.length != 10){
            alert("Data mal formatada.")
            return
        }
        
        if(horaI.length != 5 || horaT.length != 5){
            alert("Hora mal formatada.")
            return
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
        
        const res = await fetch(`${BASE_URL.url}/loja/aluguel`, options)
        if (res.status == 400) {
            alert("Ainda tem campos a serem preenchidos!")
            return
        }

        alert("ok!")
        router.push({
            pathname: "/welcome",
            query: {
                cnpj : new Buffer(cnpjLoja).toString("base64")
            }
        })
        
    }

    function delay(ms: number) {
        return new Promise( resolve => setTimeout(resolve, ms) );
    }

    const getClientes = async () => {
        const cnpj = await new Buffer(`${router.query.cnpj}`, "base64").toString("ascii")
        setCnpjLoja(cnpj)

        let tmp = []
        
        delay(2000)

        const options0 = {
            method : "POST",
            headers : {
                "Content-Type" : "application/json",
                "Access-Control-Allow-Headers": "X-Custom-Header"
            },
            body : JSON.stringify({cnpj : cnpj})
        }
        let res = await fetch(`${BASE_URL.url}/loja/clientes/listar`, options0)
             .then(data => data.json())
             .then(data => tmp.push(data))
        
        const selecaoClientes = tmp[0].map(element => { 
            
            const newObj = {key:`${new Buffer(element.cpf).toString("base64")}`, 
                            label: `${element.cpf} - ${element.nome}`, 
                            value : `${element.cpf}`}
            return newObj
        })
        if(selecaoClientes.length == 0){
            alert("Cadastre um cliente para alugar")
            router.push({
                pathname: "/welcome",
                query: {
                    cnpj : new Buffer(cnpj).toString("base64")
                }
            })
        }

        setClientes(selecaoClientes)
    }

    const getVeiculos = async () => {
        const options1 = {
            method : "GET",
            headers : {
                "Content-Type" : "application/json",
                "Access-Control-Allow-Headers": "X-Custom-Header"
            }
        }

        let tmp = []
        await fetch(`${BASE_URL.url}/veiculos`, options1)
             .then(data => data.json())
             .then(data => tmp.push(data))
        
        tmp = tmp[0]
        const selecaoVeiculos = tmp.map(element => { 
        
            const newObj = {key:`${element.id}`, 
                            label: `${element.modeloVeiculo.tipo} ${element.modeloVeiculo.modelo}`, 
                            value : `${element.id}`}
            return newObj
        })
        setVeiculos(selecaoVeiculos)         
    }

    useEffect(()=> {
        getVeiculos();
    }, [])

    useEffect(()=> {
        getClientes();
    }, [setVeiculos])

    return(
        
        <div className={styles.page}>
            <div className={styles.overlay}>
                <h1>Alugar veículo</h1>
                <form
                    onSubmit = {handleSubmit}
                    autoComplete = "off"
                >
                    <fieldset>
                    
                        <div className={styles.selection}>
                            <Select 
                                onChange={e => setCpfCliente(e.value)} 
                                placeholder="Selecione o cliente" 
                                options= {clientes}
                            />

                            <Select 
                                onChange={e => setCodigoVeiculo(e.key)} 
                                placeholder="Selecione o veículo" 
                                options= {veiculos}
                            />
                        </div>

                        <div className={styles.inputs}>
                            <div className={styles.input}>
                                <label>Data inicial</label>
                                <input 
                                    type="text"
                                    placeholder = "dd/MM/aaaa"
                                    id="dataI"
                                    value={dataI}
                                    onChange= {(e) => setDataI(e.target.value)}
                                />
                            </div>
                            <div className={styles.input}>
                                <label>Hora inicial</label>
                                <input 
                                    type="text"
                                    placeholder = "hh:mm"
                                    id="horaI"
                                    value={horaI}
                                    onChange= {(e) => setHoraI(e.target.value)}
                                />
                            </div>
                        </div>

                        <div className={styles.inputs}>
                            <div className={styles.input}>
                                <label>Data término</label>
                                <input 
                                    type="text"
                                    placeholder = "dd/MM/aaaa"
                                    id="dataT"
                                    value={dataT}
                                    onChange= {(e) => setDataT(e.target.value)}
                                />
                            </div>
                            <div className={styles.input}>
                                <label>Hora final</label>
                                <input 
                                    type="text"
                                    placeholder = "hh:mm"
                                    id="horaT"
                                    value={horaT}
                                    onChange= {(e) => setHoraT(e.target.value)}
                                />
                            </div>
                        </div>
                        <div className={styles.buttonDiv}>
                            <button type="submit">
                                Criar
                            </button>    
                        </div>
                    </fieldset>    
                </form>
            </div>
        </div>
    )
}

export default Aluguel