package connection;

import java.sql.*;

/**
 * Classe dont le seul but est d'executer les requetes SQL d'initialisation
 *
 * @author arnoux23u
 */
public class JDBCInit {

    /**
     * Constructeur prive, instanciation inutile
     */
    private JDBCInit() {
    }

    /**
     * Methode d'insertion des donnees
     *
     * @throws JDBCException exception connection
     * @throws SQLException  exception requete
     */
    public static void init() throws JDBCException, SQLException {
        Statement init = JDBCConnector.connect().createStatement();
        init.addBatch("ALTER TABLE DOSSIER ADD (CONSTRAINT fk_codecli FOREIGN KEY (code_cli) REFERENCES CLIENT (code_cli))");
        init.addBatch("ALTER TABLE DOSSIER ADD(CONSTRAINT fk_noimm FOREIGN KEY (no_imm) REFERENCES VEHICULE (no_imm))");
        init.addBatch("ALTER TABLE DOSSIER ADD(CONSTRAINT fk_agretrait FOREIGN KEY (ag_retrait) REFERENCES AGENCE (code_ag))");
        init.addBatch("ALTER TABLE DOSSIER ADD (CONSTRAINT fk_agretour FOREIGN KEY (ag_retour) REFERENCES AGENCE (code_ag))");
        init.addBatch("ALTER TABLE DOSSIER ADD (CONSTRAINT fk_agreserve FOREIGN KEY (ag_reserve) REFERENCES AGENCE (code_ag))");
        init.addBatch("ALTER TABLE VEHICULE ADD (CONSTRAINT fk_codecateg FOREIGN KEY (code_categ) REFERENCES CATEGORIE (code_categ))");
        init.addBatch("ALTER TABLE VEHICULE ADD (CONSTRAINT fk_codeag FOREIGN KEY (code_ag) REFERENCES AGENCE (code_ag))");
        init.addBatch("ALTER TABLE CATEGORIE ADD (CONSTRAINT fk_codetarif FOREIGN KEY (code_tarif) REFERENCES tarif (code_tarif))");
        init.addBatch("ALTER TABLE CALENDRIER ADD(CONSTRAINT fk_noimm1 FOREIGN KEY (no_imm) REFERENCES VEHICULE (no_imm))");
        init.addBatch("ALTER TABLE \"AUDIT\" ADD(CONSTRAINT fk_nodossier FOREIGN KEY (no_dossier) REFERENCES DOSSIER (no_dossier), CONSTRAINT fk_codecli1 FOREIGN KEY (code_cli) REFERENCES CLIENT (code_cli))");
        init.addBatch("INSERT INTO TARIF VALUES('t1',120.00,600.00,1.50,650.00,850.00,25.00)");
        init.addBatch("INSERT INTO TARIF VALUES('t2',170.00,750.00,1.80,800.00,1100.00,30.00)");
        init.addBatch("INSERT INTO TARIF VALUES('t3',210.00,900.00,2.10,1100.00,1500.00,40.00)");
        init.addBatch("INSERT INTO AGENCE VALUES('Nancy','Louvois Marc','0383911234','10, rue de la gare','Nancy','54000','France')");
        init.addBatch("INSERT INTO AGENCE VALUES('Metz','Loubard Jean','0387231111','25, avenue gambetta','Metz', '57000','France')");
        init.addBatch("INSERT INTO AGENCE VALUES('Strasbourg','Meyer Paul','0329211111','8, rue des tanneurs','Strasbourg','67000','France')");
        init.addBatch("INSERT INTO CLIENT VALUES('duvig001','duvigne gérard','16, rue des vignerons','laxou','54100')");
        init.addBatch("INSERT INTO CLIENT VALUES('dumon001','dumont nathalie','78, avenue du maine','nancy','54000')");
        init.addBatch("INSERT INTO CLIENT VALUES('delar001','delaroche claude','2, rue des tilleuls','vandoeuvre','54500')");
        init.addBatch("INSERT INTO CLIENT VALUES('delam001','delamontagne eric','5, rue des acacias','nancy','54000')");
        init.addBatch("INSERT INTO CLIENT VALUES('roule001','rouletabille claude','29, rue des lilas','Nancy','54000')");
        init.addBatch("INSERT INTO CATEGORIE VALUES('c1','citadine',4,'a1','t1')");
        init.addBatch("INSERT INTO CATEGORIE VALUES('c2','compacte',5,'a1','t2')");
        init.addBatch("INSERT INTO CATEGORIE VALUES('c3','familiale',5,'a1','t3')");
        init.addBatch("INSERT INTO VEHICULE VALUES('1234ya54','citroen','xantia2.0','blanche',TO_DATE('12-Septembre-2014','DD-MON-YYYY'),35000,'c3','Nancy')");
        init.addBatch("INSERT INTO VEHICULE VALUES('7418yc54','citroen','saxo1.1','Noire',TO_DATE('15-Août-2014','DD-MON-YYYY'),23000,'c1','Nancy')");
        init.addBatch("INSERT INTO VEHICULE VALUES('5698yd54','peugeot','106xr1.1','Grise',TO_DATE('15-Septembre-2014','DD-MON-YYYY'),26000,'c1','Nancy')");
        init.addBatch("INSERT INTO VEHICULE VALUES('6213yd54','renault','twingo','Verte',TO_DATE('20-Septembre-2014','DD-MON-YYYY'),20350,'c1','Nancy')");
        init.addBatch("INSERT INTO VEHICULE VALUES('1789xv54','citroen','xsara1.4sx','Bleue',TO_DATE('15-Mai-2013','DD-MON-YYYY'),98500,'c2','Nancy')");
        init.addBatch("INSERT INTO VEHICULE VALUES('2569yp54','peugeot','206hdi','Blanche',TO_DATE('26-Juin-2014','DD-MON-YYYY'),12000,'c2','Nancy')");
        init.addBatch("INSERT INTO VEHICULE VALUES('5213ye54','renault','laguna1.8d','Noire',TO_DATE('14-Septembre-2014','DD-MON-YYYY'),62000,'c3','Nancy')");
        init.addBatch("INSERT INTO VEHICULE VALUES('4577yp54','peugeot','406sr2.0','Noire',TO_DATE('15-Mars-2015','DD-MON-YYYY'),28000,'c3','Nancy')");
        init.addBatch("INSERT INTO VEHICULE VALUES('4588yp57','peugeot','406sr2.0','Noire',TO_DATE('15-Mars-2015','DD-MON-YYYY'),28000,'c3','Metz')");
        init.addBatch("INSERT INTO VEHICULE VALUES('5522yp57','citroen','saxo1.1','Noire',TO_DATE('15-Mars-2015','DD-MON-YYYY'),28000,'c1','Metz')");
        init.addBatch("INSERT INTO VEHICULE VALUES('3369yp57','peugeot','206hdi','Blanche',TO_DATE('26-Juin-2014','DD-MON-YYYY'),12000,'c2','Metz')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('1-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('2-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('3-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('4-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('5-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('6-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('7-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('8-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('9-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('10-Octobre-2015','DD-MON-YYYY'),'x')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('11-Octobre-2015','DD-MON-YYYY'),'x')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('12-Octobre-2015','DD-MON-YYYY'),'x')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('13-Octobre-2015','DD-MON-YYYY'),'x')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('14-Octobre-2015','DD-MON-YYYY'),'x')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('15-Octobre-2015','DD-MON-YYYY'),'x')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('16-Octobre-2015','DD-MON-YYYY'),'x')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('17-Octobre-2015','DD-MON-YYYY'),'x')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('18-Octobre-2015','DD-MON-YYYY'),'x')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('19-Octobre-2015','DD-MON-YYYY'),'x')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('20-Octobre-2015','DD-MON-YYYY'),'x')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('21-Octobre-2015','DD-MON-YYYY'),'x')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('22-Octobre-2015','DD-MON-YYYY'),'x')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('23-Octobre-2015','DD-MON-YYYY'),'x')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('24-Octobre-2015','DD-MON-YYYY'),'x')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('25-Octobre-2015','DD-MON-YYYY'),'x')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('26-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('27-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('28-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('29-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('30-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1234ya54',TO_DATE('31-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('1-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('2-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('3-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('4-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('5-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('6-Octobre-2015','DD-MON-YYYY'),'x') ");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('7-Octobre-2015','DD-MON-YYYY'),'x') ");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('8-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('9-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('10-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('11-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('12-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('13-Octobre-2015','DD-MON-YYYY'),'x')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('14-Octobre-2015','DD-MON-YYYY'),'x')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('15-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('16-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('17-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('18-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('19-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('20-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('21-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('22-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('23-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('24-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('25-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('26-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('27-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('28-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('29-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('30-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('7418yc54',TO_DATE('31-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('1-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('2-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('3-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('4-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('5-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('6-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('7-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('8-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('9-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('10-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('11-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('12-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('13-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('14-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('15-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('16-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('17-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('18-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('19-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('20-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('21-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('22-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('23-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('24-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('25-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('26-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('27-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('28-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('29-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('30-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5698yd54',TO_DATE('31-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('1-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('2-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('3-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('4-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('5-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('6-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('7-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('8-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('9-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('10-Octobre-2015','DD-MON-YYYY'),'x')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('11-Octobre-2015','DD-MON-YYYY'),'x')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('12-Octobre-2015','DD-MON-YYYY'),'x')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('13-Octobre-2015','DD-MON-YYYY'),'x')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('14-Octobre-2015','DD-MON-YYYY'),'x')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('15-Octobre-2015','DD-MON-YYYY'),'x')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('16-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('17-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('18-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('19-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('20-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('21-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('22-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('23-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('24-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('25-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('26-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('27-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('28-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('29-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('30-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('6213yd54',TO_DATE('31-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('1-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('2-Octobre-2015','DD-MON-YYYY'),'x') ");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('3-Octobre-2015','DD-MON-YYYY'),'x') ");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('4-Octobre-2015','DD-MON-YYYY'),'x') ");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('5-Octobre-2015','DD-MON-YYYY'),'x') ");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('6-Octobre-2015','DD-MON-YYYY'),'x') ");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('7-Octobre-2015','DD-MON-YYYY'),'x') ");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('8-Octobre-2015','DD-MON-YYYY'),'x') ");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('9-Octobre-2015','DD-MON-YYYY'),'x') ");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('10-Octobre-2015','DD-MON-YYYY'),'x')");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('11-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('12-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('13-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('14-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('15-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('16-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('17-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('18-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('19-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('20-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('21-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('22-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('23-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('24-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('25-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('26-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('27-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('28-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('29-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('30-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('1789xv54',TO_DATE('31-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('1-Octobre-2015','DD-MON-YYYY'),'x') ");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('2-Octobre-2015','DD-MON-YYYY'),'x') ");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('3-Octobre-2015','DD-MON-YYYY'),'x') ");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('4-Octobre-2015','DD-MON-YYYY'),'x') ");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('5-Octobre-2015','DD-MON-YYYY'),'x') ");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('6-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('7-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('8-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('9-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('10-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('11-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('12-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('13-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('14-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('15-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('16-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('17-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('18-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('19-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('20-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('21-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('22-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('23-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('24-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('25-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('26-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('27-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('28-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('29-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('30-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('2569yp54',TO_DATE('31-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('1-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('2-Octobre-2015','DD-MON-YYYY'),'x') ");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('3-Octobre-2015','DD-MON-YYYY'),'x') ");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('4-Octobre-2015','DD-MON-YYYY'),'x') ");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('5-Octobre-2015','DD-MON-YYYY'),'x') ");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('6-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('7-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('8-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('9-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('10-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('11-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('12-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('13-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('14-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('15-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('16-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('17-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('18-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('19-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('20-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('21-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('22-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('23-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('24-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('25-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('26-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('27-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('28-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('29-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('30-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('5213ye54',TO_DATE('31-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('1-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('2-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('3-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('4-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('5-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('6-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('7-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('8-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('9-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('10-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('11-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('12-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('13-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('14-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('15-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('16-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('17-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('18-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('19-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('20-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('21-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('22-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('23-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('24-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('25-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('26-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('27-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('28-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('29-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('30-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO CALENDRIER VALUES('4577yp54',TO_DATE('31-Octobre-2015','DD-MON-YYYY'),null)");
        init.addBatch(" INSERT INTO DOSSIER VALUES(1,TO_DATE('1-Octobre-2015','DD-MON-YYYY'),TO_DATE('5-Octobre-2015','DD-MON-YYYY'),null,null,null,'t1','x',null,null,null,'duvig001','7418yc54','Nancy','Nancy','Nancy')");
        init.addBatch(" INSERT INTO DOSSIER VALUES(2,TO_DATE('1-Octobre-2015','DD-MON-YYYY'),TO_DATE('5-Octobre-2015','DD-MON-YYYY'),null,null,null,'t1',null,null,null,null,'dumon001','2569yp54','Nancy','Nancy','Nancy')");
        init.addBatch(" INSERT INTO DOSSIER VALUES(3,TO_DATE('2-Octobre-2015','DD-MON-YYYY'),TO_DATE('10-Octobre-2015','DD-MON-YYYY'),null,null,null,'t1','x',null,null,null,'delar001','1789xv54','Nancy','Nancy','Nancy')");
        init.addBatch(" INSERT INTO DOSSIER VALUES(4,TO_DATE('2-Octobre-2015','DD-MON-YYYY'),TO_DATE('5-Octobre-2015','DD-MON-YYYY'),null,null,null,'t1',null,null,null,null,'delam001','5213ye54','Nancy','Nancy','Nancy')");
        init.addBatch(" INSERT INTO DOSSIER VALUES(5,TO_DATE('6-Octobre-2015','DD-MON-YYYY'),TO_DATE('7-Octobre-2015','DD-MON-YYYY'),null,null,null,'t2','x',null,null,null,'roule001','7418yc54','Nancy','Nancy','Nancy')");
        init.addBatch(" INSERT INTO DOSSIER VALUES(6,TO_DATE('10-Octobre-2015','DD-MON-YYYY'),TO_DATE('15-Octobre-2015','DD-MON-YYYY'),null,null,null,'t1',null,null,null,null,'duvig001','6213yd54','Nancy','Strasbourg','Nancy')");
        init.addBatch(" INSERT INTO DOSSIER VALUES(7,TO_DATE('10-Octobre-2015','DD-MON-YYYY'),TO_DATE('20-Octobre-2015','DD-MON-YYYY'),null,null,null,'t1','x',null,null,null,'dumon001','1234ya54','Nancy','Nancy','Nancy')");
        init.addBatch(" INSERT INTO DOSSIER VALUES(8,TO_DATE('13-Octobre-2015','DD-MON-YYYY'),TO_DATE('14-Octobre-2015','DD-MON-YYYY'),null,null,null,'t3',null,null,null,null,'delar001','7418yc54','Nancy','Nancy','Nancy')");
        init.addBatch(" INSERT INTO DOSSIER VALUES(9,TO_DATE('13-Octobre-2015','DD-MON-YYYY'),TO_DATE('14-Octobre-2015','DD-MON-YYYY'),null,null,null,'t2',null,null,null,null,'delar001','6213yd54','Nancy','Nancy','Nancy')");
        init.addBatch(" INSERT INTO DOSSIER VALUES(10,TO_DATE('21-Octobre-2015','DD-MON-YYYY'),TO_DATE('25-Octobre-2015','DD-MON-YYYY'),null,null,null,'t1','x',null,null,null,'roule001','1234ya54','Nancy','Nancy','Nancy')");
        init.addBatch(" CREATE OR REPLACE TRIGGER majKilometrage AFTER UPDATE ON DOSSIER REFERENCING NEW AS location FOR EACH ROW BEGIN UPDATE VEHICULE V SET V.KILOMETRES =:location.KIL_RETOUR WHERE V.NO_IMM = :location.NO_IMM; END;");
        init.addBatch("CREATE OR REPLACE TRIGGER logAudit AFTER UPDATE ON DOSSIER REFERENCING NEW AS location FOR EACH ROW DECLARE v_DUREE NUMBER(3); v_NOMCLI CLIENT.NOM%TYPE; v_MARQUE VEHICULE.MARQUE%TYPE; v_MODELE VEHICULE.MODELE%TYPE; BEGIN v_DUREE := :location.DATE_RETOUR - :location.DATE_RETRAIT + 1; IF v_DUREE >7 THEN SELECT NOM INTO v_NOMCLI FROM CLIENT WHERE CODE_CLI = :location.CODE_CLI; SELECT MARQUE, MODELE INTO v_MARQUE, v_MODELE  FROM VEHICULE WHERE NO_IMM = :location.NO_IMM; INSERT INTO \"AUDIT\" VALUES(:location.NO_DOSSIER, (SELECT SYSDATE FROM DUAL), :location.CODE_CLI, v_NOMCLI, v_MARQUE, v_MODELE); END IF; END;");
        init.addBatch("COMMIT");
        init.executeBatch();
    }

    /**
     * Methode de creation des tables
     *
     * @throws SQLException  exception requete
     * @throws JDBCException exception connection
     */
    public static void create() throws SQLException, JDBCException {
        Statement init = JDBCConnector.connect().createStatement();
        init.addBatch("CREATE TABLE AGENCE ( code_ag   VARCHAR2(10), nomresp   VARCHAR2(30) NOT NULL, numtel    VARCHAR2(12) NOT NULL, rue       VARCHAR2(40), ville     VARCHAR2(25), codpostal VARCHAR2(5), pays      VARCHAR2(20), PRIMARY KEY (code_ag) )");
        init.addBatch("CREATE TABLE CALENDRIER ( no_imm   VARCHAR2(10), datejour DATE, paslibre CHAR(1), PRIMARY KEY (no_imm, datejour) )");
        init.addBatch("CREATE TABLE CATEGORIE ( code_categ  VARCHAR2(3), libelle     VARCHAR2(30)                                                                       NOT NULL, nbpers      NUMBER(2)                                                                          NOT NULL, type_permis VARCHAR2(2) CHECK (type_permis in ('a', 'a1', 'b', 'c', 'd', 'e_b', 'e_c', 'e_d')) NOT NULL, code_tarif  VARCHAR2(3), PRIMARY KEY (code_categ) )");
        init.addBatch("CREATE TABLE CLIENT ( code_cli  VARCHAR2(8), nom       VARCHAR2(40) NOT NULL, rue       VARCHAR2(40) NOT NULL, ville     VARCHAR2(25) NOT NULL, codpostal VARCHAR2(5)  NOT NULL, PRIMARY KEY (code_cli) )");
        init.addBatch("CREATE TABLE DOSSIER ( no_dossier   NUMBER(6), date_retrait DATE NOT NULL, date_retour  DATE NOT NULL, date_effect  DATE, kil_retrait  NUMBER(6), kil_retour   NUMBER(6), type_tarif   VARCHAR2(5), assur        CHAR(1), nbjour_fact  NUMBER(3), nbsem_fact   NUMBER(3), remise       NUMBER(4, 2), code_cli     VARCHAR2(8), no_imm       VARCHAR2(10), ag_retrait   VARCHAR2(10), ag_retour    VARCHAR2(10), ag_reserve   VARCHAR2(10), PRIMARY KEY (no_dossier) )");
        init.addBatch("CREATE TABLE TARIF ( code_tarif  VARCHAR2(3), tarif_jour  NUMBER(6, 2) NOT NULL, tarif_hebdo NUMBER(6, 2) NOT NULL, tarif_kil   NUMBER(4, 2) NOT NULL, tarif_w500  NUMBER(6, 2) NOT NULL, tarif_w800  NUMBER(6, 2) NOT NULL, tarif_asur  NUMBER(6, 2) NOT NULL, PRIMARY KEY (code_tarif) )");
        init.addBatch("CREATE TABLE VEHICULE ( no_imm     VARCHAR2(10), marque     VARCHAR2(20) NOT NULL, modele     VARCHAR2(20) NOT NULL, couleur    VARCHAR2(20), date_achat DATE CHECK (TO_CHAR(date_achat, 'yyyy') > 1998), kilometres NUMBER(6) CHECK (kilometres >= 0), code_categ VARCHAR2(3), code_ag    VARCHAR2(10), PRIMARY KEY (no_imm) )");
        init.addBatch("CREATE TABLE \"AUDIT\" (no_dossier NUMBER(6), dateSys DATE, code_cli VARCHAR2(8), nom VARCHAR2(40), marque VARCHAR2(20), modele VARCHAR2(20), PRIMARY KEY(no_dossier))");
        init.addBatch("COMMIT");
        init.executeBatch();
    }

}
