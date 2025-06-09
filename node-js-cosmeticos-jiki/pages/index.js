import Header from '../components/Header';
import Banner from '../components/Banner';
import Categories from '../components/Categories';
import Subscription from '../components/Subscription';
import Footer from '../components/Footer';

const produtos = [
  { id: 1, nome: 'Shampoo', preco: 29.9, imagem: '' },
  { id: 2, nome: 'Condicionador', preco: 32.9, imagem: '' },
  { id: 3, nome: 'Hidratante', preco: 25.0, imagem: '' },
  { id: 4, nome: 'Perfume', preco: 99.9, imagem: '' },
  { id: 5, nome: 'Sabonete', preco: 9.9, imagem: '' },
  { id: 6, nome: 'Óleo Capilar', preco: 39.9, imagem: '' },
  { id: 7, nome: 'Creme Facial', preco: 49.9, imagem: '' },
  { id: 8, nome: 'Protetor Solar', preco: 59.9, imagem: '' },
  { id: 9, nome: 'Máscara Capilar', preco: 44.9, imagem: '' },
  { id: 10, nome: 'Body Splash', preco: 34.9, imagem: '' },
  { id: 11, nome: 'Esfoliante', preco: 19.9, imagem: '' },
  { id: 12, nome: 'Leave-in', preco: 27.9, imagem: '' },
];

export default function Home() {
  return (
    <div>
      <Header />
      <Banner />
      <Categories />
      {/* Grade de produtos responsiva */}
      <div
        style={{
          display: 'grid',
          gridTemplateColumns: 'repeat(auto-fit, minmax(220px, 1fr))',
          gap: 24,
          maxWidth: 900,
          width: '100%',
          margin: '40px auto'
        }}
      >
        {produtos.map(produto => (
          <div
            key={produto.id}
            style={{
              background: '#fff',
              borderRadius: 12,
              boxShadow: '0 2px 8px #e5d4ee33',
              padding: 16,
              textAlign: 'center'
            }}
          >
            <img src={produto.imagem} alt={produto.nome} style={{ width: 120, height: 120, objectFit: 'cover', borderRadius: 8, marginBottom: 12 }} />
            <h4 style={{ margin: '8px 0', color: '#b0d8bc' }}>{produto.nome}</h4>
            <div style={{ color: '#000000', fontWeight: 'bold', fontSize: 18 }}>R$ {produto.preco.toFixed(2)}</div>
          </div>
        ))}
      </div>
      <Subscription />
      <Footer />
    </div>
  );
}

