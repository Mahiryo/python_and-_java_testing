import math
# Arredondamentos e operações inteiras


# Você já ouviu falar de piso e teto? Mas no contexto numérico?
# Então, vai uma dica, piso salarial e teto salarial.

#  Explicando melhor

# O piso salarial é o menor valor de salário que pode ser pago a uma categoria
# profissional por sua jornada de trabalho. Já o teto salarial é o maior salário que
# pode ser pago para realização de dado serviço. É o oposto de piso salarial.

# Dado um salário w = 3345,61. Qual o seu teto e piso salarial?

# Para respondera estaquestão quepoderá sermuito útil para números decimais também,
# vamos usar três funções do Python:


# • floor (piso em inglês) – retorna o maior valor inteiro menor que w
# • ceil (teto em inglês) – retorna o menor valor inteiro maior que w
# • round (arredondamento em inglês) – retorna o valor inteiro mais próximo de a


# Exercício 4)
# Dado o valor de w anteriormente, import as funções floor, ceil da biblioteca math e determine o
# piso, o teto e o arredondamento.
w = 3345.61

teto_w = math.ceil(w)
chao_w = math.floor(w)
bola_w = round(w)


print("teto_w: ", teto_w)
print("chao_w: ", chao_w)
print("bola_w: ", bola_w)


# Exercício5)
# • Teste a função round usando casas decimais, inclusive 0 casas
# • Por que não importamos o round?

### funções de arredontamento removem informações drelevantes ao négocio, trazendo imperfeições que se acumulam ao passar do tempo
###