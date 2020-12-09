import { useRouter } from "next/router"
import { useEffect, useState } from "react"
import Select from 'react-select'
import BASE_URL from "../components/const.url"
import styles from './index.module.scss'

interface Opcoes {
    key : string,
    label: string,
    value: string
}[]

export default function Home() {
    const router = useRouter()
    const [selectedCnpj, setSelectedCnpj] = useState<string>("")
    const [opcoes, setOpcoes] = useState<Opcoes[]>([])

    const getData = async () => {
        const options = {
            method : "GET",
            headers : {
                "Content-Type" : "application/json",
                "Access-Control-Allow-Headers": "X-Custom-Header"
            }
        }
        console.log(process.env.BASE_URL)
        const tmp = []
        const res = await fetch(`${BASE_URL.url}/lojas`, options)
                    .then(data => data.json())
                    .then(data => tmp.push(data))
        
        const selecaoLojas = tmp[0].map(element => { 
            
            const newObj = {key:`${new Buffer(element.cnpj).toString("base64")}`, 
                            label: `${element.nome}`, 
                            value : `${element.cnpj}`}
            return newObj
        })
        setOpcoes(selecaoLojas)
        // console.log(opcoes)          
    }

    useEffect(() => {
        getData()
    }, [])
        
    return( 
    <body className = {styles.main}>
        
        <div className = {styles.content}>
            <h1 className ={styles.title}>Central</h1>
            
            <div className={styles.acesso}>
                <div className={styles.select}>

                    <Select 
                        onChange={e => setSelectedCnpj(e.key)} 
                        placeholder="Selecione sua loja" 
                        options= {opcoes}
                    />
                </div>
                <div className = {styles.link}>
                    <span onClick={(e) => {
                        e.preventDefault()
                        if(selectedCnpj) {
                            router.push({
                                pathname: `/welcome`,
                                query: {cnpj : selectedCnpj}
                            })
                        }
                    }}>Ir</span>
                </div>
            </div>

            <div className={styles.criar}>
                <span onClick={(e) => {
                    e.preventDefault()
                    router.push("loja")
                }}>Criar nova loja</span>
            </div>
        </div>
        
    </body>
    )
}
