SELECT CODE_AG
FROM VEHICULE V
HAVING COUNT(DISTINCT CODE_CATEG) = (SELECT COUNT(1) FROM CATEGORIE)
GROUP BY CODE_AG;