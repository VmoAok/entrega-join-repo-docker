import styles from '../styles/Subscription.module.css';

const Subscription = () => {
  return (
    <div className={styles.subscription}>
      <p>Acompanhe-nos e ganhe cupom 10%!</p>
      <input type="email" placeholder="Email" />
      <button>inscreva-se</button>
    </div>
  );
};

export default Subscription;
