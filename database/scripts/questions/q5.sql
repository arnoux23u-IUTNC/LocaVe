select distinct C.NOM, C.VILLE, C.CODPOSTAL from CLIENT C
inner join DOSSIER D on C.CODE_CLI = D.CODE_CLI
inner join VEHICULE V on V.NO_IMM = D.NO_IMM
where (select count(MODELE) from VEHICULE) = 2;



