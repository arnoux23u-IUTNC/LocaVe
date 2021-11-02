select c.CODE_CLI, count( distinct v.MODELE) from CLIENT C
inner join DOSSIER D on C.CODE_CLI = D.CODE_CLI
inner join VEHICULE v on v.NO_IMM = d.NO_IMM
having count(distinct v.MODELE) = 2
group by c.CODE_CLI
order by c.CODE_CLI;



