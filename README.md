Resolução de um desafio com o desenvolvimento de backend com a versão "simpliicada" do PicPay, utilizando Java Spring.

Dependências utilizadas:

-H2 <br />
-Spring Data JPA. <br />
-Spring Web. <br />
-Bean Validation. <br />

Endpoints:

[get] /transactions - Retorna todas as transações
[get] /transactions/{id} - Busca uma transação por id
[post] /transactions - Adiciona uma nova transação

Modelo do payload de uma nova transação:

{
  senderId: ,
  receiverId: ,
  amount: 
}

Modelo de erro esperado (RFC 7807):
  
{
  status: ,
  timestamp: ,
  type: ,
  title: ,
  detail: ,
  userMessage: ,
  errors: [
  name: ,
    userMessage: ,
  ]
}

O array erros retorna os erros do Bean Validation, por exemplo, "Id não pode ser nulo".

