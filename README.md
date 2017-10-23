# biblioteca-rest

Dois microserviços que expõem os recursos livros e autores;
Cada um dos microserviços implementam operações de CRUD para seu recurso;

# Implantação

Para implantar a aplicação em sua máquina, siga as etapas descritas abaixo:
- Certifique-se de possuir o docker, docker-compose, maven e o git instalado em sua máquina;
- Abra seu melhor terminal, navegue até a pasta onde você quer ter este projeto baixado;
- Execute o comando `git clone https://github.com/pedroviniv/biblioteca-rest.git`
- Após terminar o processo de clonagem do repositório, entre na pasta principal do mesmo e execute o comando:
	- `sh start.sh`

Se tudo tiver ocorrido bem, as portas 8081 e 8082 do seu computador serão expostas e através delas você será capaz de interagir com a aplicação. Na 8081 estará disponível o microserviço de livros e na 8082, o microserviço de autores.