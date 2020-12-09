import { useEffect, useState } from "react"

import { useRouter } from "next/router"
import styles from './welcome.module.scss'
import Link from 'next/link'

const Menu = () => {
    const router = useRouter()
    const [cnpjLoja, setCnpjLoja] = useState<string>("")

    const getHeader = async () => {
        const cnpj = await new Buffer(`${router.query.cnpj}`, "base64").toString("ascii")
        setCnpjLoja(cnpj)
    }

    useEffect(()=> {
        getHeader()
    }, [])
    
    return( 
        <body className = {styles.main}>
            
            <div className = {styles.content}>
                    <strong className= {styles.title}>Bem vindo!</strong>
                    
                    <div className={styles.link}>
                        <li>
                            <Link href={`/aluguel?cnpj=${new Buffer(cnpjLoja).toString("base64")}`}>
                                <a>Novo aluguel</a>
                            </Link>
                        </li>
                        <li>
                            <Link href={`pagar?cnpj=${new Buffer(cnpjLoja).toString("base64")}`}>
                                <a>Pagar aluguel</a>
                            </Link>
                        </li>
                    </div>

                    <div className={styles.criar}>
                        <span
                            onClick= {(e) => {
                                e.preventDefault()
                                console.log(cnpjLoja)
                                router.push({
                                    pathname: "/cliente",
                                    query : {
                                        cnpj : new Buffer(cnpjLoja).toString("base64")
                                    }
                                })
                            }}
                        >
                            Criar novo cliente
                        </span>
                        <span>
                            Listar clientes da loja
                        </span>
                    </div>
            </div>
            
        </body>
        )
}

export default Menu