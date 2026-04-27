# C07_BDRTRABALHO
IDEIA ORIGINAL :
<img width="911" height="681" alt="image" src="https://github.com/user-attachments/assets/2f4cdb5f-9057-45f0-b276-10b9652b1113" />

OBJETIVOS INICIAIS :CRIACAO DE UM BANCO DE DADOS PARA O CONTROLE E GERENCIAMENTO DE UM VESTIBULAR DE LARGA ESCALA COMO  O ENEM

#INCOERENCIAS:
FACULDADE,CURSO E VESTIBULAR:
A modelagem inicial apresentava inconsistência ao não separar claramente os conceitos de oferta e participação no processo seletivo.
Cursos e faculdades não devem ser tratados como entidades dependentes entre si, pois um mesmo curso pode ser oferecido por diferentes faculdades sem redundância de dados.
A relação correta ocorre no contexto de uma oferta, onde uma faculdade oferece um curso em um determinado vestibular, com atributos próprios como número de vagas e nota de corte.
Essa relação é representada por uma tabela associativa que envolve curso, faculdade e vestibular simultaneamente, caracterizando uma relação ternária no modelo conceitual.
Já a participação do vestibulando no processo seletivo é independente dessa oferta, sendo corretamente modelada como uma relação entre vestibulando e vestibular
Devido as dificuldades imposta por uma relacao ternaria o objetivo deve ser a decomposicao em relacoes binarias melhores o que exige a criacao de uma nova entidade
