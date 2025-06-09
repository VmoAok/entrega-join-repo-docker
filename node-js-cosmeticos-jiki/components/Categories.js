import styles from '../styles/Categories.module.css';
import Link from 'next/link';
import { useState } from 'react';

const Categories = () => {
  const [open, setOpen] = useState(false);

  // Detecta se está em mobile (opcional, pode ser só pelo CSS)
  // Aqui só controla o botão e a classe
  return (
    <div>
      <button
        className={styles.filterBtn}
        onClick={() => setOpen(o => !o)}
      >
        -
      </button>
      <div className={`${styles.categories} ${open ? styles.open : ''}`}>
        <Link href="/produtos" passHref>
          <div className={styles.category} style={{ cursor: 'pointer' }}>Produtos</div>
        </Link>
        <div className={styles.category}>Box Gift</div>
        <div className={styles.category}>Promoções</div>
        
      </div>
    </div>
  );
};

export default Categories;
