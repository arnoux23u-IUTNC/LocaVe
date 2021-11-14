select (TARIF_JOUR * (select to_date(?, 'yyyy-mm-dd') - to_date(?, 'yyyy-mm-dd') from dual))
from TARIF
inner join CATEGORIE C2 on TARIF.CODE_TARIF = C2.CODE_TARIF
inner join VEHICULE V on C2.CODE_CATEG = V.CODE_CATEG
where V.MODELE = ?;