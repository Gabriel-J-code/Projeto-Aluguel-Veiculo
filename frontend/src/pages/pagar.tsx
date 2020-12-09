import styles from './pagar.module.scss'
import Link from 'next/link'
import Select from 'react-select'
import { FormEvent, useState } from 'react'

const Pagar = () => {

    const [aluguelId, setAluguelId] = useState<string>("")
    const [valor, setValor] = useState<string>("")
    const [teste, setTeste] = useState<boolean>(false)
    const handleSubmit = (event : FormEvent)=> {
        event.preventDefault()
        setTeste(!teste)
    }

    return(
        <div className={styles.page}>
            <div className={styles.overlay}>
                <div className={styles.header}>
                    <h1 className={styles.title}>Pagamento</h1>
                    <Link href="">
                        <div className={styles.voltar}></div>
                    </Link>
                </div>
                <form
                    autoComplete="false"
                    onSubmit={handleSubmit}
                >
                    <fieldset>
                        <div className={styles.selection}>
                            <Select 
                                onChange={e => setAluguelId(e.value)} 
                                placeholder="Selecione o aluguel" 
                                // options= {}
                            />
                        </div>
                        {teste &&
                            <>
                                <div className = {styles.valor}>
                                    <label htmlFor="">À pagar:</label>
                                    <h1>R$ 2216,00</h1>
                                </div>
                                
                                <div className={styles.input}>
                                    <label htmlFor= "">Valor recebido:</label>
                                    <input 
                                        placeholder = "R$ 0,00"
                                        id="valor"
                                        value={`R$ ${valor}`}
                                        onChange= {(e) => setValor(
                                            e.target.value.substr(3, e.target.value.length)
                                        )}
                                    />
                                </div>
                            </>
                        }
                        <div className={styles.buttonDiv}>
                            <button type="submit">
                                Pagar
                            </button>    
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    )
}

export default Pagar