# sonar 

- criar projeto manualmente
- testar projeto manualmente
- gerar tocker global squ_cb253e315b2f9b80b2a1cdea015e92ac41a7a6ba

- ir para aba administration/security/user
- clicar na linha do usuario na coluna token, para conferir o tokem	


#jenkins

- gerenciar jenkins / plugins / extensões disponiveis
- adicionar plutins do sonarqube
- gerenciar jenkins / credencials
- Stores scoped to Jenkins
- clicar em domain global 
- clicar em add credencials
- selecionar em secret text
- preencher o campo secret com o token do sonarqube
- clicar em create
- voltar para a tela principal
- gerenciar jenkins / system
- ir para sonar cube scanner
- marcar [] environment variables
- clicar em add sonarqube
- voltar para a tela principal
- clicar em tools
- maven instalacoes
- adicionar mavem 
- SonarQube Scanner instalações
- Adicionar SonarQube Scanner
- obs: deve ser informado o ip interno do container do sonar e nao o ip do gateway
 	



