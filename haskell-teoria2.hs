module Teoria where

--TAD Inventado

data Inventado = B | M deriving (Show , Eq, Ord)

--Probarlo en comp.( Escribir "B" -> Intro).

--Operaciones

contrario :: Inventado -> Inventado
contrario x = if (x==B) then M
		       else B

{-contrario  B = M
contrario  M = B
-}

todo :: Inventado -> Inventado -> Inventado
todo x y = if (x==B) then y
			else M
{-
todo B x = x
todo M _ = M
-}
{-
todo  B B = B
todo  B M = M
todo  M B = M
todo  M M = M
-}

algo :: Inventado -> Inventado -> Inventado
algo x y = if (x==B) then B
			else y
{-
algo  B B = B
algo  B M = B
algo  M B = B
algo  M M = M
-}

algoMensaje :: Inventado -> Inventado -> String
algoMensaje x y = if (x==B) then "BIEN"
					else if (y==B) then "BIEN"
					else "MAL"
					
--EXCEPCIONES
operacion :: Inventado -> Inventado
operacion x = error "Operacion no realizada"

--Equivalencias

t1Inventado = contrario (contrario B)
t2Inevnatdo = algo B t1Inventado

--TAD NAT
--Operacion parametrizada

data NAT = Cero | Suc NAT deriving (Show, Eq, Ord)

-- Operaciones
iguales :: NAT -> NAT -> Bool
iguales Cero Cero = True
iguales Cero x = False	
iguales x Cero = False
iguales (Suc x) (Suc y) = iguales x y

suma :: NAT -> NAT -> NAT
suma Cero x = x
suma (Suc x) y = Suc (suma x y)

toInt :: NAT -> Int
toInt Cero = 0
toInt (Suc x) = 1 +toInt x

toNAT :: Int -> NAT
toNAT 0 = Cero
toNAT x = Suc (toNAT (x - 1))

--Los generadores nunca son libre a excepción de los NAT.

data INT = Cero' | Suc' INT | Pre' INT deriving (Show, Eq, Ord)

fnINT :: INT -> INT
fnINT Cero' = Cero'
fnINT (Suc'(Pre' x)) = fnINT (Pre' (Suc' x))
fnINT (Pre' (Suc' x)) = fnINT (x)
fnINT x = x


--TAD Conjunto (De un tipo de elemnto => LO QUE SEA => Per si todo lo que
--hay dentro de lo mismo

--TAD Polimorfico

data Conjunto a = Vacio | CNV a (Conjunto a) deriving (Show, Eq, Ord)

fn :: (Eq a)=> Conjunto a -> Conjunto a
fn Vacio = Vacio
fn (CNV e c) = if (pertenece e c) then (fn c)
				else (CNV e (fn c))

pertenece :: (Eq a)=> a -> Conjunto a -> Bool
pertenece e Vacio = False
pertenece e (CNV e2 c) = if (e==e2) then True	
						else pertenece e c
						

 