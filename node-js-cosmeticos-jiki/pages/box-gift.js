import { useState } from 'react';
import { useRouter } from 'next/router';

const caixasPreDefinidas = [
  {
    id: 1,
    nome: 'Box Relax',
    descricao: 'Inclui sabonete, hidratante e vela aromática.',
    imagem: 'https://via.placeholder.com/180x120?text=Box+Relax'
  },
  {
    id: 2,
    nome: 'Box Spa',
    descricao: 'Inclui máscara facial, óleo corporal e esfoliante.',
    imagem: 'https://via.placeholder.com/180x120?text=Box+Spa'
  },
  {
    id: 3,
    nome: 'Box Amor',
    descricao: 'Inclui perfume, creme de mãos e bombons.',
    imagem: 'https://via.placeholder.com/180x120?text=Box+Amor'
  }
];

export default function BoxGift() {
  const [customizando, setCustomizando] = useState(false);

  return (
    <div style={{ maxWidth: 900, margin: '40px auto', padding: 24 }}>
      <h2 style={{ color: '#b0d8bc', marginBottom: 24 }}>Escolha sua Caixa de Presente</h2>
      {!customizando ? (
        <>
          <div style={{
            display: 'grid',
            gridTemplateColumns: 'repeat(auto-fit, minmax(220px, 1fr))',
            gap: 24,
            marginBottom: 32
          }}>
            {caixasPreDefinidas.map(box => (
              <div key={box.id} style={{
                background: '#fff',
                borderRadius: 12,
                boxShadow: '0 2px 8px #e5d4ee33',
                padding: 16,
                textAlign: 'center'
              }}>
                <img src={box.imagem} alt={box.nome} style={{ width: 180, height: 120, objectFit: 'cover', borderRadius: 8, marginBottom: 12 }} />
                <h4 style={{ margin: '8px 0', color: '#f99d75' }}>{box.nome}</h4>
                <div style={{ color: '#7e807f', marginBottom: 8 }}>{box.descricao}</div>
                <button style={{
                  background: '#b0d8bc',
                  color: '#fff',
                  border: 'none',
                  borderRadius: 8,
                  padding: '8px 18px',
                  fontWeight: 'bold',
                  cursor: 'pointer',
                  marginTop: 8
                }}>
                  Escolher esta box
                </button>
              </div>
            ))}
          </div>
          <button
            onClick={() => setCustomizando(true)}
            style={{
              background: '#f99d75',
              color: '#fff',
              border: 'none',
              borderRadius: 8,
              padding: '12px 28px',
              fontWeight: 'bold',
              fontSize: 16,
              cursor: 'pointer',
              display: 'block',
              margin: '0 auto'
            }}
          >
            Quero customizar minha box
          </button>
        </>
      ) : (
        <div style={{
          background: '#fff',
          borderRadius: 12,
          boxShadow: '0 2px 8px #e5d4ee33',
          padding: 24,
          maxWidth: 500,
          margin: '0 auto'
        }}>
          <h3 style={{ color: '#b0d8bc', marginBottom: 16 }}>Monte sua Box Personalizada</h3>
          <form>
            <input type="text" placeholder="Nome da box" required style={{
              width: '100%',
              padding: 10,
              marginBottom: 16,
              borderRadius: 6,
              border: '1px solid #ccc'
            }} />
            <textarea placeholder="Mensagem especial (opcional)" style={{
              width: '100%',
              padding: 10,
              marginBottom: 16,
              borderRadius: 6,
              border: '1px solid #ccc',
              minHeight: 60
            }} />
            <button type="submit" style={{
              width: '100%',
              padding: 12,
              background: '#b0d8bc',
              color: '#fff',
              border: 'none',
              borderRadius: 6,
              fontWeight: 'bold',
              cursor: 'pointer'
            }}>
              Salvar box personalizada
            </button>
          </form>
          <button
            onClick={() => setCustomizando(false)}
            style={{
              marginTop: 16,
              background: 'transparent',
              color: '#b0d8bc',
              border: 'none',
              cursor: 'pointer',
              textDecoration: 'underline'
            }}
          >
            Voltar para boxes pré-definidas
          </button>
        </div>
      )}
    </div>
  );
}