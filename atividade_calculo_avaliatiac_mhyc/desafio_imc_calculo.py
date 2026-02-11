import time





# Parte 1) Cálculo do IMC
# Solicite ao usuário que digite:
# • Peso (em kg)
# • Altura (em metros)
# • Calcule o IMC usando a fórmula: imc = peso/(altura**2)
    # Exemplo de saída:
    #       Digite seu peso (kg): 70
    #       Digite sua altura (m): 1.75
    #       Seu IMC é 22.86




def float_check(value):
    try:
        float(value)
        return True
    except ValueError:
        return False
    except TypeError: 
        return False





print(f"Iniciando Calculado IMC!")

nome_usuario = input("Insira seu nome:")
while (nome_usuario == ""):
    time.sleep(1)
    print("...")
    nome_usuario = input(f"Insira seu nome (você precisa inserir algo!):")



# //////////////////////////////////////////////////////////////////////////////////////////////
# ///////////////////////////////////////////////////[/////////////////////////////////////////
# ///////////////////////////////////////////////////////////////////////////////////////////


def calc_start():

    nome_pessoa = input("Insira o nome da pessoa que fará o teste IMC:")
    while (nome_pessoa == ""):
        time.sleep(1)
        print("...")
        nome_pessoa = input("Insira o nome da pessoa que fará o teste IMC (você precisa inserir algo!):")

    altura_pessoa = (input(f"Insira a altura de {nome_pessoa} em CENTIMETROS:"))
    while ((altura_pessoa == "") or (float_check(altura_pessoa) == False)):
        time.sleep(1)
        print("...")
        altura_pessoa = (input(f"Insira a altura de {nome_pessoa} em CENTIMETROS (você precisa inserir algo valido!):"))

    peso_pessoa = (input(f"Insira o peso de {nome_pessoa} em KG:"))
    while ((peso_pessoa == "") or (float_check(peso_pessoa) == False)):
        time.sleep(1)
        print("...")
        peso_pessoa = (input(f"Insira o peso de {nome_pessoa} em KG (você precisa inserir algo valido!):"))



    altura_pessoa = float(float(altura_pessoa)/100)
    peso_pessoa = float(peso_pessoa)
    value_IMC = float(peso_pessoa / (altura_pessoa**2))


    if value_IMC > 30:
        if value_IMC < 40:
            if value_IMC < 35:
                IMC_status = "Obesidade tipo I"
            else:
                IMC_status = "Obesidade tipo II"
        elif value_IMC < 50:
            IMC_status = "Obesidade tipo III"
        else:
            IMC_status = "Obesidade tipo IV"

    elif value_IMC > 25:
        if value_IMC >= 27:
            IMC_status = "Sobrepeso grau II"
        else:
            IMC_status = "Sobrepeso grau I"
    elif value_IMC >= 18.5:
        IMC_status = "Peso normal" 
    else:
        IMC_status = "Baixo Peso"   

    print(f"INFORME {nome_pessoa} QUE O RESULTADO IMC É: {value_IMC}, OU SEJA, {nome_pessoa} ESTA {IMC_status} ")


    time.sleep(0.25) 
    print("...")
    time.sleep(0.5) 
    print(".....")
    time.sleep(0.99) 
    print(".......")


    print(f"{nome_usuario}")


    time.sleep(0.25) 
    print("...")
    time.sleep(0.5) 
    print(".....")
    time.sleep(0.99) 
    print(".......")

    continuar = str(input(f"Deseja calcular o IMC de outra pessoa? (s / n):"))
    print(continuar)
    while (continuar != ("s" or "n")):
        print("responda de forma valida!")
        continuar = str(input(f"Deseja calcular o IMC de outra pessoa? (s / n):"))
        time.sleep(0.125)

    if continuar == "s":
        print("INICIANDO NOVA CALCULADORA")
        time.sleep(0.5)
        print("##########################################################################################################")
        print("##########################################################################################################")
        print("##########################################################################################################")
        time.sleep(0.5)
        calc_start()
    else:
        print("ADEUS! o7 ")
        return False

calc_start()
