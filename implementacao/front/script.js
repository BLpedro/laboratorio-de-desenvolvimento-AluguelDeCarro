function cadastrarVeiculo(){
    event.preventDefault();
    const marca = document.getElementById("marca").value;
    const modelo = document.getElementById("modelo").value;
    const ano = document.getElementById("ano").value;
    const matricula = document.getElementById("matricula").value;
    const placa = document.getElementById("placa").value;

    fetch('http://localhost:8080/automoveis', {
        method: 'POST',
        mode: 'cors',
        headers: {  
            'Content-Type': 'application/json'   
    },
    body: JSON.stringify({
        marca: marca,
        modelo: modelo,
        ano: ano,
        matricula: matricula,
        placa: placa
    })})
        .then(response => response.json())
    .then(data => {
      console.log('Usuario adicionado', data);
      localStorage.setItem("id", data.matricula);
    })
    .catch(error => console.error('Erro ao adicionar usuario:', error));
          alert("Cadastrado com sucesso!!!");
}


function cadastrarUsuario(){
    event.preventDefault();
    const nome = document.getElementById("nome").value;
    const cpf = document.getElementById("cpf").value;
    const senha = document.getElementById("senha").value;
  const select = document.getElementById("tipoUsuario");
    select.addEventListener("change", function() {
    const selecao = select.value;
    console.log("Usuário selecionou:", selecao);
  });
    const rg = document.getElementById("rg").value;
    const endereco = document.getElementById("endereco").value; 
    const profissao = document.getElementById("profissao").value;
    const tipo = document.getElementById("tipoAgente").value;

const rendimento = 
  parseFloat(document.getElementById("rendimentoUM").value || 0) +
  parseFloat(document.getElementById("rendimentoDOIS").value || 0) +
  parseFloat(document.getElementById("rendimentoTRES").value || 0);


    const selecao = document.getElementById("tipoUsuario").value;

    fetch('http://localhost:8080/usuarios', {
        method: 'POST',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            nome: nome,
            cnpjCpf: cpf,
            senha: senha,
            rg: rg,
            endereco: endereco,
            profissao: profissao,
            somaRendimento: rendimento,
            selecao: selecao,
            tipo: tipo
        })
    })
        .then(response => response.json())
        .then(data => {
            console.log('Usuario adicionado', data);
            localStorage.setItem("id", data.id);
        })
        .catch(error => console.error('Erro ao adicionar usuario:', error));
    alert("Cadastrado com sucesso!!!"); 
}


// login
function loginUsuario(event){
    event.preventDefault();
    const cnpjCpf = document.getElementById("cpf").value;
    const senha = document.getElementById("senha").value;

    fetch('http://localhost:8080/usuarios/login', {
        method: 'POST',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            cnpjCpf: cnpjCpf,
            senha: senha
        })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Credenciais inválidas");
        }
        return response.json();
    })
    .then(data => {
        console.log('Login realizado com sucesso', data);
        localStorage.setItem("id", data.id);

        alert("Login realizado com sucesso!!!");
        window.location.href = "index.html";
    })
    .catch(error => {
        console.error('Erro ao realizar login:', error);
        alert("Falha no login: " + error.message);
    });

    return false;
}


async function popularListaAutomoveis() {
  const listaDiv = document.getElementById("listaAutomoveis");
  if (!listaDiv) return;

  try {
    const response = await fetch("http://localhost:8080/automoveis", { mode: "cors" });
    const automoveis = await response.json();
    console.log(automoveis);
    listaDiv.innerHTML = "";

    if (automoveis.length === 0) {
      listaDiv.innerHTML = "<p>Nenhum automóvel cadastrado.</p>";
      return;
    }

    const tabelaBody = document.querySelector("#tabelaCarros tbody");


    automoveis.forEach(automovel => {
const linha = document.createElement("tr");

      linha.innerHTML = `
        <td>${automovel.id}</td>
        <td>${automovel.ano}</td>
        <td>${automovel.marca}</td>
        <td>${automovel.modelo}</td>
        <td>${automovel.placa}</td>
        <td data-label="Ações">
        <button onclick="alugarAutomovel(${automovel.id})">Alugar</button>
      </td>
      `;

      tabelaBody.appendChild(linha);
    });
  } catch (error) {
    listaDiv.innerHTML = "<span style='color:red'>Erro ao carregar automóveis.</span>";
    console.error(error);
  }
}
popularListaAutomoveis();


function alugarAutomovel(id) {
  // cria o HTML do formulário
  const formHtml = `
    <h3>Formulário de Aluguel - Automóvel ${id}</h3>
    <form onsubmit="enviarFormulario(${id}); return false;">

      <label for="nome">Id do Agente:</label><br>
      <input type="number" id="nome" name="nome" required><br><br>

      <label for="duracao">Duração do empréstimo em dias:</label><br>
      <input type="number" id="duracao" name="duracao" required><br><br>

      <button onclick="postAluguel(${id})" type="submit">Confirmar Aluguel</button>
    </form>
  `;

  // insere o formulário na div
  document.getElementById("formulario").innerHTML = formHtml;
}


function postAluguel(id) {
  event.preventDefault();
  const idAutomovel = id;
  const idAgente = document.getElementById("nome").value;
  const duracao = document.getElementById("duracao").value;
  const clienteID = localStorage.getItem("id"); // pega do login

  fetch("http://localhost:8080/alugueis", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      clienteID: clienteID,
      automovelID: idAutomovel,  // cuidado: JSON é case-sensitive
      agenteID: idAgente,
      quantidadeDias: duracao,
      statusPedido: "pendente"
    })
  })
  .then(response => {
    if (!response.ok) {
      throw new Error("Erro ao alugar automóvel");
    }
    return response.json();
  })
  .then(data => {
    console.log("Aluguel realizado com sucesso", data);
    alert("Aluguel realizado com sucesso!");
  })
  .catch(error => {
    console.error("Erro ao realizar aluguel:", error);
    alert("Erro ao realizar aluguel: " + error.message);
  });
}


async function pendentesUsuario() {
  const clienteID = localStorage.getItem("id");
  const listaDiv = document.getElementById("listaPendentes");
  if (!listaDiv) return;

  try {
    const response = await fetch("http://localhost:8080/alugueis", { mode: "cors" });
    if (!response.ok) {
      throw new Error("Erro ao buscar aluguéis");
    }

    const alugueis = await response.json();
    console.log("Todos os aluguéis:", alugueis);

    // filtra apenas os do cliente logado
    const alugueisCliente = alugueis.filter(aluguel => aluguel.clienteID == clienteID);

    listaDiv.innerHTML = "";

    if (alugueisCliente.length === 0) {
      listaDiv.innerHTML = "<p>Nenhum aluguel encontrado para este usuário.</p>";
      return;
    }

    // cria tabela
    let tabelaHtml = `
      <table border="1">
        <thead>
          <tr>
            <th>ID Aluguel</th>
            <th>Automóvel</th>
            <th>Agente</th>
            <th>Duração (dias)</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
    `;

    alugueisCliente.forEach(aluguel => {
      tabelaHtml += `
        <tr>
          <td>${aluguel.id}</td>
          <td>${aluguel.automovelID}</td>
          <td>${aluguel.agenteID}</td>
          <td>${aluguel.quantidadeDias}</td>
          <td>${aluguel.statusPedido}</td>
        </tr>
      `;
    });

    tabelaHtml += "</tbody></table>";
    listaDiv.innerHTML = tabelaHtml;

  } catch (error) {
    console.error("Erro ao carregar aluguéis:", error);
    listaDiv.innerHTML = "<span style='color:red'>Erro ao carregar aluguéis.</span>";
  }
}
pendentesUsuario()

async function pendentesAgente() {
  const agenteID = localStorage.getItem("id");
  const listaDiv = document.getElementById("listaPendentesAgente");
  if (!listaDiv) return;

  try {
    const response = await fetch("http://localhost:8080/alugueis", { mode: "cors" });
    if (!response.ok) {
      throw new Error("Erro ao buscar aluguéis");
    }

    const alugueis = await response.json();
    console.log("Todos os aluguéis:", alugueis);

    // filtra apenas os do agente logado
    const alugueisAgente = alugueis.filter(aluguel => aluguel.agenteID == agenteID);

    listaDiv.innerHTML = "";

    if (alugueisAgente.length === 0) {
      listaDiv.innerHTML = "<p>Nenhum aluguel encontrado para este agente.</p>";
      return;
    }

    // cria tabela
    let tabelaHtml = `
      <table border="1">
        <thead>
          <tr>
            <th>ID Aluguel</th>
            <th>Cliente</th>
            <th>Automóvel</th>
            <th>Duração (dias)</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
    `;

    alugueisAgente.forEach(aluguel => {
      tabelaHtml += `
        <tr>
          <td>${aluguel.id}</td>
          <td>${aluguel.clienteID}</td>
          <td>${aluguel.automovelID}</td>
          <td>${aluguel.quantidadeDias}</td>
          <td>${aluguel.statusPedido}</td>
        </tr>
      `;
    });

    tabelaHtml += "</tbody></table>";
    listaDiv.innerHTML = tabelaHtml;

  } catch (error) {
    console.error("Erro ao carregar aluguéis do agente:", error);
    listaDiv.innerHTML = "<span style='color:red'>Erro ao carregar aluguéis do agente.</span>";
  }
}
pendentesAgente()