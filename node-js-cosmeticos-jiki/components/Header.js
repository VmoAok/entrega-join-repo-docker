import styles from '../styles/Header.module.css';

const Header = () => {
  return (
    <header className={styles.header}>
      <div>
        <h1 className={styles.logo}>
          <span className={styles.l1}>J</span>
          <span className={styles.l2}>i</span>
          <span className={styles.l3}>k</span>
          <span className={styles.l4}>i</span>
          <span className={styles.space}> </span>
          <span className={styles.s1}>S</span>
          <span className={styles.s2}>k</span>
          <span className={styles.s3}>i</span>
          <span className={styles.s4}>n</span>
        </h1>
        <p className={styles.tagline}>Skincare sem fronteira</p>
      </div>
      <div className={styles.search}>
        <input type="text" placeholder="Buscar produtos..." />
        <button type="submit">Buscar</button>
      </div>
      <div className={styles.cart}></div>
      <nav className={styles.nav}>
        <a href="/">Início</a>
        <a href="/about">Sobre</a>
        <a href="/contact">Contato</a>
        <a href="/login">Login</a>
      </nav>

    </header>
  );
};

export default Header;