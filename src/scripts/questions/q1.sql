select distinct modele, V.NO_IMM
from VEHICULE V, CALENDRIER C
where CODE_CATEG = 'c3'
and V.NO_IMM = C.NO_IMM
and DATEJOUR between to_date('2015-10-23', 'YYYY/MM/DD') and to_date('2015-10-25', 'YYYY/MM/DD')
and PASLIBRE is null;