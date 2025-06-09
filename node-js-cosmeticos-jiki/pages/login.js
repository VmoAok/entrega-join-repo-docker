import { useState } from 'react';
import styles from '../styles/Login.module.css';

export default function Login() {
  const [tab, setTab] = useState('login');

  return (
    <div className={styles.loginContainer}>
      <div className={styles.tabBar}>
        <button
          onClick={() => setTab('login')}
          className={`${styles.tabBtn} ${tab === 'login' ? styles.active : ''}`}
        >
          Login
        </button>
        <button
          onClick={() => setTab('register')}
          className={`${styles.tabBtn} ${tab === 'register' ? styles.active : ''}`}
        >
          Cadastro
        </button>
      </div>
      {tab === 'login' ? (
        <form>
          <input type="email" placeholder="E-mail" required className={styles.formInput} />
          <input type="password" placeholder="Senha" required className={styles.formInput} />
          <button type="submit" className={styles.formBtn}>Entrar</button>
        </form>
      ) : (
        <form>
          <input type="text" placeholder="Nome" required className={styles.formInput} />
          <input type="email" placeholder="E-mail" required className={styles.formInput} />
          <input type="password" placeholder="Senha" required className={styles.formInput} />
          <button type="submit" className={styles.formBtn}>Cadastrar</button>
        </form>
      )}
    </div>
  );
}