import { useState } from 'react';
import { useRouter } from 'next/router';

const produtosFake = [
  { id: 1, nome: 'Shampoo', categoria: 'Cabelos', preco: 29.9, imagem: 'https://via.placeholder.com/120x120?text=Shampoo' },
  { id: 2, nome: 'Condicionador', categoria: 'Cabelos', preco: 32.9, imagem: 'https://via.placeholder.com/120x120?text=Condicionador' },
  { id: 3, nome: 'Hidratante', categoria: 'Corpo', preco: 25.0, imagem: 'https://via.placeholder.com/120x120?text=Hidratante' },
  { id: 4, nome: 'Perfume', categoria: 'Perfumes', preco: 99.9, imagem: 'https://via.placeholder.com/120x120?text=Perfume' },
  { id: 5, nome: 'Sabonete', categoria: 'Corpo', preco: 9.9, imagem: 'https://via.placeholder.com/120x120?text=Sabonete' },
  { id: 6, nome: 'Óleo Capilar', categoria: 'Cabelos', preco: 39.9, imagem: 'https://via.placeholder.com/120x120?text=Óleo+Capilar' },
];

const categorias = ['Todos', 'Cabelos', 'Corpo', 'Perfumes'];

export default function Produtos() {
  const [filtro, setFiltro] = useState('Todos');
  const [expandido, setExpandido] = useState(false);
  const [isPressed, setIsPressed] = useState(false);
  const router = useRouter();

  const handleTodosClick = () => {
    setFiltro('Todos');
    setExpandido(exp => !exp);
  };

  const produtosFiltrados = filtro === 'Todos'
    ? produtosFake
    : produtosFake.filter(p => p.categoria === filtro);

  return (
    <div style={{ display: 'flex', minHeight: '80vh', background: '#f8f8fa' }}>
      {/* Botão Home com seta, fora do filtro */}
      <div style={{ position: 'absolute', left: 10, top: 20, zIndex: 10 }}>
        <button
          onClick={() => router.push('/')}
          onMouseDown={() => setIsPressed(true)}
          onMouseUp={() => setIsPressed(false)}
          onMouseLeave={() => setIsPressed(false)}
          style={{
            display: 'flex',
            alignItems: 'center',
            gap: 8,
            background: isPressed ? '#ce9ee0' : '#b0d8bc',
            color: '#fff',
            border: 'none',
            borderRadius: 40,
            fontWeight: 'bold',
            cursor: 'pointer',
            padding: '10px 18px',
            fontSize: 11,
            boxShadow: '0 2px 8px rgba(148, 54, 199, 0.2)',
            transition: 'background 0.2s'
          }}
        >
          {/* Seta minimalista SVG */}
          <svg width="18" height="18" viewBox="0 0 18 18" fill="none">
            <path d="M11.5 15L6 9L11.5 3" stroke="#fff" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
          </svg>
          Home
        </button>
      </div>

      {/* Filtro lateral */}
      <aside style={{ width: 200, padding: 24, background: '#fff', borderRadius: 16, margin: 24, boxShadow: '0 2px 12px #e5d4ee33', height: 'fit-content' }}>
        <h3 style={{ marginBottom: 16, color: '#b0d8bc' }}>Filtrar</h3>
        <button
          onClick={handleTodosClick}
          style={{
            display: 'block',
            width: '100%',
            marginBottom: 10,
            padding: 10,
            background: filtro === 'Todos' ? '#f99d75' : '#f5f5f5',
            color: filtro === 'Todos' ? '#fff' : '#333',
            border: 'none',
            borderRadius: 8,
            fontWeight: 'bold',
            cursor: 'pointer',
            transition: 'background 0.2s'
          }}
        >
          Todos
        </button>
        {expandido && categorias.slice(1).map(cat => (
          <button
            key={cat}
            onClick={() => setFiltro(cat)}
            style={{
              display: 'block',
              width: '100%',
              marginBottom: 10,
              padding: 10,
              background: filtro === cat ? '#f99d75' : '#f5f5f5',
              color: filtro === cat ? '#fff' : '#333',
              border: 'none',
              borderRadius: 8,
              fontWeight: 'bold',
              cursor: 'pointer',
              transition: 'background 0.2s'
            }}
          >
            {cat}
          </button>
        ))}
      </aside>

      {/* Grid de produtos */}
      <main style={{ flex: 1, padding: 32 }}>
        <h2 style={{ color: '#b0d8bc', marginBottom: 24 }}>Produtos</h2>
        <div style={{
          display: 'grid',
          gridTemplateColumns: 'repeat(auto-fit, minmax(180px, 1fr))',
          gap: 24
        }}>
          {produtosFiltrados.map(produto => (
            <div key={produto.id} style={{
              background: '#fff',
              borderRadius: 12,
              boxShadow: '0 2px 8px #e5d4ee33',
              padding: 16,
              textAlign: 'center'
            }}>
              <img src={produto.imagem} alt={produto.nome} style={{ width: 120, height: 120, objectFit: 'cover', borderRadius: 8, marginBottom: 12 }} />
              <h4 style={{ margin: '8px 0', color: '#f99d75' }}>{produto.nome}</h4>
              <div style={{ color: '#b0d8bc', fontWeight: 'bold', marginBottom: 8 }}>{produto.categoria}</div>
              <div style={{ color: '#d84242', fontWeight: 'bold', fontSize: 18 }}>R$ {produto.preco.toFixed(2)}</div>
            </div>
          ))}
        </div>
      </main>
    </div>
  );
}