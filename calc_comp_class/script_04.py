import math;

# Exercício 2)
# Crie um programa em Python que:
# • Importe a função pow do módulo math.
# • Atribua valores para duas variáveis:
# o c (ex.: 4)
# o d (ex.: 5)
# • Calcule usando pow() e armazene em variáveis:
# o c_elevado_ao_quadrado → c²
# o c_elevado_ao_cubo → c³
# o c_elevado_a_quarta → c⁴
# o c_elevado_a_d → c elevado a d
# • Exiba os resultados com mensagens no formato:

#  c elevado ao quadrado = <valor>
#  c elevado ao cubo = <valor>
#  c elevado a quarta = <valor>
#  c elevado a d = <valor>
# Desafio extra: permita que o usuário digite os valores de c e d em vez de fixá-los no código.


c = input("give me a number:")

d = input("give me a number:")

# li o desafio depois, perdão pela falta da versão sem o desafio

c = float(c)
d = float(d)


c_elevado_ao_quadrado = pow(c, 2)
c_elevado_ao_cubo = pow(c, 3)
c_elevado_a_quarta = pow(c, 4)
c_elevado_a_d = pow(c, d)

print("Potência do primeiro valor ao quadrado:", c_elevado_ao_quadrado )
print("Potência ao primeiro valor cubo:", c_elevado_ao_cubo)
print("Potência do primeiro valor à quarta:", c_elevado_a_quarta)
print("Potência ao segundo valor:", c_elevado_a_d)