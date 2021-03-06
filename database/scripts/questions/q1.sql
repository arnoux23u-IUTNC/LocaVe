SELECT NO_IMM
FROM VEHICULE V
         INNER JOIN CATEGORIE C ON V.CODE_CATEG = C.CODE_CATEG
WHERE C.LIBELLE LIKE 'citadine'
MINUS
SELECT V.NO_IMM
FROM CALENDRIER C
         INNER JOIN VEHICULE V ON V.NO_IMM = C.NO_IMM
WHERE DATEJOUR BETWEEN TO_DATE('2015-10-6', 'YYYY-MM-DD') AND TO_DATE('2015-10-14', 'YYYY-MM-DD')
  AND PASLIBRE LIKE 'x';