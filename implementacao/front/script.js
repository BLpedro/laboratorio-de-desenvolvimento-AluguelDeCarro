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
