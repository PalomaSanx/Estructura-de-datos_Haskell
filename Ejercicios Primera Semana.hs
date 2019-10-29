module Ejercicios where

-- **************************************************** Tema 2 - Descripcion formal en Haskell *******************************************************************************************************************
-- **************************************************** Tema 2 - Descripcion formal en Haskell *******************************************************************************************************************
-- **************************************************** Tema 2 - Descripcion formal en Haskell *******************************************************************************************************************
-- **************************************************** Tema 2 - Descripcion formal en Haskell *******************************************************************************************************************
-- **************************************************** Tema 2 - Descripcion formal en Haskell *******************************************************************************************************************
-- **************************************************** Tema 2 - Descripcion formal en Haskell *******************************************************************************************************************
-- **************************************************** Tema 2 - Descripcion formal en Haskell *******************************************************************************************************************
-- **************************************************** Tema 2 - Descripcion formal en Haskell *******************************************************************************************************************
-- **************************************************** Tema 2 - Descripcion formal en Haskell *******************************************************************************************************************
-- **************************************************** Tema 2 - Descripcion formal en Haskell *******************************************************************************************************************
-- **************************************************** Tema 2 - Descripcion formal en Haskell *******************************************************************************************************************
-- **************************************************** Tema 2 - Descripcion formal en Haskell *******************************************************************************************************************

-- ******************************************************************
-- ******************************************************************
-- ************************************* Ejercicio 1. TAD Boolean ***
-- ******************************************************************
-- ******************************************************************

data Boolean = T | F deriving (Eq, Show)

-- Términos Sintácticos:
t1Boolean = not' (not' (or' T (and' F (not' F))))
t2Boolean = and' t1Boolean F

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- OPERACIONES (EJERCICIOS):
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

not' :: Boolean -> Boolean
not' T = F
not' F = T

and' :: Boolean -> Boolean -> Boolean
and' T b = b
and' F b = F

or' :: Boolean -> Boolean -> Boolean
or' T b = T
or' F b = b

-- ******************************************************************
-- ******************************************************************
-- ************************************* Ejercicio 2. TAD Bool3 *****
-- ******************************************************************
-- ******************************************************************

data Bool3 = T' | F' | M deriving (Eq, Show)
         
-- Términos Sintácticos:
t1Bool3 = not3 (not3 (or3 T' (and3 M (not3 M))))
t2Bool3 = and3 t1Bool3 M

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- OPERACIONES (EJERCICIOS):
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

not3 :: Bool3 -> Bool3
not3 T' = F'
not3 F' = T'
not3 M = M

and3 :: Bool3 -> Bool3 -> Bool3
and3 T' b = b
and3 F' b = F'
and3 M b = if (b == F') then F' else M

or3 :: Bool3 -> Bool3 -> Bool3
or3 T' b = T'
or3 F' b = b
or3 M b = if (b == T') then T' else M

or3' :: Bool3 -> Bool3 -> Bool3 -- Solucion utilizando las funciones not3 y and3
or3' x y = not3 (and3 (not3 x) (not3 y))

and3' :: Bool3 -> Bool3 -> Bool3 -- Solucion utilizando las funciones not3 y and3
and3' x y = not3 (or3 (not3 x) (not3 y))

xor3 :: Bool3 -> Bool3 -> Bool3
xor3 x y = or3 (and3 x (not3 y)) (and3 (not3 x) y)

-- ******************************************************************
-- ******************************************************************
-- ************************************* Ejercicio 3. TAD NAT *******
-- ******************************************************************
-- ******************************************************************

data NAT = Cero | Suc NAT deriving (Show, Eq, Ord)

-- Términos Sintácticos:
unoNat = Suc Cero
dosNat = Suc unoNat
tresNat = Suc dosNat
cuatroNat = Suc tresNat
cincoNat = Suc cuatroNat

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- OPERACIONES (EJEMPLOS):
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

igualNAT :: NAT -> NAT -> Bool
igualNAT Cero Cero = True
igualNAT _ Cero = False
igualNAT Cero _ = False
igualNAT (Suc n) (Suc m) = igualNAT n m

sumaNAT :: NAT -> NAT -> NAT
sumaNAT Cero n = n
sumaNAT (Suc n) m = Suc (sumaNAT n m)

toInt :: NAT -> Int
toInt Cero = 0
toInt (Suc x) = 1 + toInt x

toNAT :: Int -> NAT
toNAT 0 = Cero
toNAT x = Suc (toNAT (x-1))

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- OPERACIONES (EJERCICIOS):
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

mayorNAT :: NAT -> NAT -> Bool
mayorNAT Cero _ = False
mayorNAT _ Cero = True
mayorNAT (Suc n) (Suc m) = mayorNAT n m

restaNAT :: NAT -> NAT -> NAT
restaNAT Cero Cero = Cero
restaNAT Cero n = error "No existen numeros negativos en los naturales"
restaNAT n Cero = n
restaNAT (Suc n) (Suc m) = restaNAT n m

multNAT :: NAT -> NAT -> NAT
multNAT Cero n = Cero 
multNAT n Cero = Cero
multNAT n (Suc m) = sumaNAT n (multNAT n m) -- Sumamos n tantas veces como se rellame a la funcion. Se rellama tantas veces como valores tengamos en el segundo parametros: 2x3 = 2+2+2 (3 veces)


-- ***************************************************************************
-- ***************************************************************************
-- ************************************* Ejercicio 4. ENTREGABLE DE ESTE AÑO *
-- ***************************************************************************
-- ***************************************************************************

-- Falta la operacion mod

------------------------------------------------
-- 28/10/2016 - Grupo B
-- ---------------------------------------------	

-- EJERCICIO 1
data Movimiento = Mov Int Int deriving (Show, Eq, Ord)

-- a) No son libres, puesto que distintos terminos sintacticos representan el mismo valor:
--	  Por ejemplo, Mov 5 25 es igual que Mov 0 20.

-- b)
fnMovimiento :: Movimiento -> Movimiento
fnMovimiento	(Mov i 0) = if (i<0) then fnMovimiento (Mov 0 (-i)) else (Mov i 0)
fnMovimiento	(Mov 0 d) = if (d<0) then fnMovimiento (Mov (-d) 0) else (Mov 0 d)
fnMovimiento	(Mov i d) = if (i<0) then fnMovimiento (Mov 0 (d-i)) 
						  else if (d<0) then fnMovimiento (Mov (i-d) 0) 
						  else fnMovimiento (Mov (i-1) (d-1)) 
				 
-- c)
sumaMovimiento :: Movimiento -> Movimiento -> Movimiento
sumaMovimiento 	(Mov i1 d1) (Mov i2 d2) = fnMovimiento (Mov (i1+i2) (d1+d2))


-- ******************************************************************
-- ******************************************************************
-- ************************************* Ejercicio 5. TAD Bool10 ****
-- ******************************************************************
-- ******************************************************************

data Bool10 = TRUE Int deriving (Show, Eq)
 
 -- a) No son libres, puesto que distintos terminos sintacticos representan el mismo valor:
--	  Por ejemplo, todo valor mayor que T 9 representa el T 9.

-- b) Axiomas de Equivalencia.
fnBool10 :: Bool10 -> Bool10
fnBool10 (TRUE n) = if n>=9 then TRUE 9
			  else if n<=0 then TRUE 0
			  else TRUE n

-- Términos Sintácticos:
t1Bool10 = not10 (not10 (or10 (TRUE 18) (and10 (TRUE 2) (not10 (TRUE 7)))))
t2Bool10 = and10 t1Bool10 (TRUE 2)			  
			  
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- OPERACIONES (EJERCICIOS):
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

not10 :: Bool10 -> Bool10
not10 (TRUE n) = not10' (fnBool10 (TRUE n))

not10' :: Bool10 -> Bool10
not10' (TRUE n) = TRUE (9-n)

and10 :: Bool10 -> Bool10 -> Bool10
and10 (TRUE n) (TRUE m) = if (n<m) then fnBool10 (TRUE n)
						  else fnBool10 (TRUE m)
						  
or10 :: Bool10 -> Bool10 -> Bool10
or10 (TRUE n) (TRUE m) = if (n>m) then fnBool10 (TRUE n)
						  else fnBool10 (TRUE m)

-- ******************************************************************
-- ******************************************************************
-- ************************************* Ejercicio 6. TAD BoolNat ***
-- ******************************************************************
-- ******************************************************************

data BoolNat = Cero' | Suc' BoolNat deriving (Show, Eq, Ord)

fnBoolNat :: BoolNat -> BoolNat
fnBoolNat Cero' = Cero'
fnBoolNat (Suc' Cero') = Suc' Cero'
fnBoolNat (Suc'(Suc' n)) = fnBoolNat n

-- andBoolNat
andBoolNat :: BoolNat -> BoolNat -> BoolNat
andBoolNat n m = andBoolNatAux (fnBoolNat n) (fnBoolNat m)

andBoolNatAux :: BoolNat -> BoolNat -> BoolNat
andBoolNatAux n m = if n==Cero' || m==Cero' then Cero' else Suc' Cero'

-- orBoolNat
orBoolNat :: BoolNat -> BoolNat -> BoolNat
orBoolNat n m = orBoolNatAux (fnBoolNat n) (fnBoolNat m)

orBoolNatAux :: BoolNat -> BoolNat -> BoolNat
orBoolNatAux n m = if n==(Suc' Cero') || m==(Suc' Cero') then Suc' Cero' else Cero'

-- notBoolNat
notBoolNat :: BoolNat -> BoolNat
notBoolNat n = notBoolNatAux (fnBoolNat n)

notBoolNatAux :: BoolNat -> BoolNat
notBoolNatAux Cero' = (Suc' Cero')
notBoolNatAux (Suc' Cero') = Cero'