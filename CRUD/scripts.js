async function carregarClientes() {
  const response = await fetch('http://localhost:8080/api/clientes');
  const clientes = await response.json();
  const tbody = document.querySelector('#tabelaClientes tbody');
  tbody.innerHTML = '';
  clientes.forEach(c => {
    tbody.innerHTML += `<tr><td>${c.id}</td><td>${c.name}</td><td>${c.email}</td></tr>`;
  });
}

async function carregarEstoque() {
  const response = await fetch('http://localhost:8080/api/estoque');
  const estoque = await response.json();
  const tbody = document.querySelector('#tabelaEstoque tbody');
  tbody.innerHTML = '';
  estoque.forEach(e => {
    tbody.innerHTML += `<tr><td>${e.id}</td><td>${e.product}</td><td>${e.quantity}</td></tr>`;
  });
}