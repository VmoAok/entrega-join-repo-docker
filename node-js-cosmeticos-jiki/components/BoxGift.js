import { useEffect, useState } from 'react';

const BoxGift = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/products')
      .then(response => response.json())
      .then(data => setProducts(data));
  }, []);

  return (
    <div>
      <h2>BoxGift</h2>
      <ul>
        {products.map(product => (
          <li key={product.id}>
            <img src={product.image} alt={product.name} />
            <h3>{product.name}</h3>
            <p>R$ {product.price}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ProductList;
