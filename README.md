# RestaurantesDB

Projeto desenvolvido para plataformas Android.
O App segue o padrão MVC para organização, possui um sistema de login que pede apenas o nome de usuário,
e verifica em uma lista se aquele usuário é novo,caso seja, ele é adicionado, se não pega-se a referência 
dessa lista para ver se o usuário já votou hoje. 
Como não há um banco de dados e essa lista é volátil, o usuário sempre será novo.
Existem 6 restaurantes cadastrados.
Os restaurantes no vetor restaurantesDaSemana são removidos da lista de restaurantes abertos para votação.
O vetor restaurantes da semana é populado de acordo com o dia da semana pelo método Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
Ao votar o usuário recebe em uma variável a data do seu último voto e ela é comparada com a data de hoje, caso seja a mesma, ele não
pode votar.
Ao sair do aplicativo esses dados são perdidos, com o código servindo apenas para simulação.
As verificações e contagem de votos são geradas por métodos aleatórios.
O método carregaLista() carrega a lista de forma assíncrona, utilizando threads para melhorar o desempenho da aplicação.
Em Android, os métodos que alteram a View devem ser executados na thread principal, através do método runOnUiThread()
Para fazer um ListView mais bonito, utilizou-se uma classe RestaurantesAdapter que herda de BaseAdapter.
Para ver o resultado é necessário votar.

Para melhorias no projeto seria essencial implementar um banco de dados que guarde a contagem de votos e fazer uma comunicação web 
utilizando um sistema de login e senha e que guarde os votos de todos os usuários, fazendo a votação no período da manhã e as 
11.30 seria decidido o restaurante mais votado, não sendo mais possível de votar até o outro dia de manhã. Seria interessante
criar um perfil para cada restaurante, incluindo preços, endereço, e o cardápio de cada um para os usuários analisarem antes de votar.
