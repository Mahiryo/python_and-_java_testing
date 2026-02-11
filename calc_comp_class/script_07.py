import math

# Exercício 6)

# Arredonde os números a seguir, exibindo sem casas decimais:

# x1=1.456
# x2=3.678
# x3=7.5

# Exercício 7)
# Voltando ao caso do 
# math.floor e ceil,
#  por padrão estas funções retornam valores inteiros. 
# Podemos determinar que os valores resultantes possuam ponto flutuante (float):

x1=1.456
x2=3.678
x3=7.5

round_x1 = round(x1, 0)
round_x2 = round(x2, 0)
round_x3 = round(x3, 0)

print("round_x1:", round_x1)
print("round_x2:", round_x2)
print("round_x3:", round_x3)


resultado = math.floor(1.456)
print(resultado)
print(type(resultado))
resultado_float = float(math.floor(1.456))
print(resultado_float)
print(type(resultado_float))