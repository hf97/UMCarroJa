import javax.swing.*;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;
import java.io.PrintWriter;
import java.io.File;

public class UMCarroJa implements Serializable{
    int numeroCarro;
    private Map<String, Utilizador> utilizadores;
    private Map<String, Veiculo> veiculos;
    private Map<Integer, Aluguer> alugueres;
    private Map<String, List<String>> listVeicProp;
    private Map<String, List<Integer>> histCli;
    private Map<String, List<Integer>> histProp;
    private Map<String, List<Integer>> histVeic;
    private List<String> livres;
    private List<String> ocupados;
    private int idAluguer;

    public UMCarroJa(){
        numeroCarro = 0;
        this.utilizadores = new HashMap<String, Utilizador>();
        this.veiculos = new HashMap<String, Veiculo>();
        this.alugueres = new HashMap<Integer, Aluguer>();
        this.listVeicProp = new HashMap<String, List<String>>();
        this.histCli = new HashMap<String, List<Integer>>();
        this.histProp = new HashMap<String, List<Integer>>();
        this.histVeic = new HashMap<String, List<Integer>>();
        this.livres = new ArrayList<String>();
        this.ocupados = new ArrayList<String>();
        this.idAluguer = 0;
    }

    public UMCarroJa(int numeroCarro, Collection<Utilizador> u, Collection<Veiculo> v, Collection<Aluguer> al, Collection<String> lvp, Collection<List<Integer>> cli, Collection<List<Integer>> prop, Collection<List<Integer>> veic, Collection<String> liv, Collection<String> ocu, Integer idAluguer){
        this.numeroCarro = numeroCarro;
        this.utilizadores = new HashMap<String, Utilizador>(u.size());
        for(Utilizador a : u){
            this.utilizadores.put(a.getEmail(), a.clone());
        }
        this.veiculos = new HashMap<String, Veiculo>(v.size());
        for(Veiculo a : v){
            this.veiculos.put(a.getMatricula(), a.clone());
        }
        this.alugueres = new HashMap<Integer, Aluguer>();
        for(Aluguer a : al){
            this.alugueres.put(a.getId(), a.clone());
        }
        this.listVeicProp = new HashMap<String, List<String>>();
        this.histCli = new HashMap<String, List<Integer>>();
        this.histProp = new HashMap<String, List<Integer>>();
        this.histVeic = new HashMap<String, List<Integer>>();
        this.livres = new ArrayList<String>();
        for(String a : liv){
            this.livres.add(a);
        }
        this.ocupados = new ArrayList<String>();
        for(String a : ocu){
            this.ocupados.add(a);
        }
        this.idAluguer = idAluguer;
    }

    public UMCarroJa(UMCarroJa p){
        this.numeroCarro = p.getNumeroCarro();
        this.utilizadores = p.getUtilizadores();
        this.veiculos = p.getVeiculos();
        this.alugueres = p.getAlugueres();
        this.listVeicProp = p.getListVeicProp();
        this.histCli = p.getHistCli();
        this.histProp = p.getHistProp();
        this.histVeic = p.getHistVeic();
        this.livres = p.getLivres();
        this.ocupados = p.getOcupados();
        this.idAluguer = p.getIdAluguer();
    }

    public Map<String, Utilizador> getUtilizadores() {
        return this.utilizadores;
    }

    public Map<String, Veiculo> getVeiculos() {
        return this.veiculos;
    }

    public Map<Integer, Aluguer> getAlugueres() {
        return this.alugueres;
    }

    public Map<String, List<String>> getListVeicProp() { return listVeicProp; }

    public Map<String, List<Integer>> getHistCli() {return this.histCli;}

    public Map<String, List<Integer>> getHistProp() {return this.histProp;}

    public Map<String, List<Integer>> getHistVeic() {return this.histVeic;}

    public List<String> getLivres(){
        return this.livres;
    }

    public List<String> getOcupados() {
        return ocupados;
    }

    public int getIdAluguer() {
        return idAluguer;
    }

    /**
     * Define o id de aluguer
     *
     */
    public void setIdAluguer(int idAluguer) {
        this.idAluguer = idAluguer;
    }

    /**
     * Retorna o numero do id de carro
     *
     */
    public int getNumeroCarro() {
        return this.numeroCarro;
    }

    /**
     * Adiciona um um elemento a lista de historico de cliente
     *
     */
    public void adicionaHistCli(String email, List<Integer> l) {this.histCli.put(email, l);}

    /**
     * Adiciona um um elemento a lista de historico de proprietario
     *
     */
    public void adicionaHistProp(String email, List<Integer> l) {this.histProp.put(email, l);}

    /**
     * Adiciona um um elemento a lista de historico de veiculo
     *
     */
    public void adicionaHistVeic(String matricula, List<Integer> l) {this.histVeic.put(matricula, l);}

    /**
     * Adiciona um um elemento a lista de veiculos do proprietario
     *
     */
    public void adicionaListVeicProp(String email, List<String> l){
        this.listVeicProp.put(email, l);
    }

    /**
     * Adiciona um utilizador
     *
     */
    public void adicionaUtilizador (Utilizador u){
        this.utilizadores.put(u.getEmail(), u.clone());
    }

    /**
     * Adiciona um veiculo
     *
     */
    public void adicionaVeiculo (Veiculo v){ this.veiculos.put(v.getMatricula(), v.clone()); }

    /**
     * Adiciona um aluguer
     *
     */
    public void adicionaAluguer (Aluguer al){
        this.alugueres.put(al.getId(), al.clone());
    }

    /**
     * Retorna um utilizador especifico
     *
     */
    public Utilizador getUtilizador(String email){
        return this.utilizadores.get(email);
    }

    /**
     * Apaga utilizador especifico
     *
     */
    public void removeUtilizador(){
        Scanner input = new Scanner(System.in);
        System.out.print("Email do utilizador: ");
        String email = input.next();
        this.utilizadores.remove(email);
    }

    /**
     * Apaga utilizadores
     *
     */
    public void removeTodosUtilizadores(){
        this.utilizadores.clear();
    }

    /**
     * Apaga veiculo especifico
     *
     */
    public void removeVeiculo(){
        Scanner input = new Scanner(System.in);
        System.out.print("Matricula do veiculo: ");
        String matricula = input.next();
        this.utilizadores.remove(matricula);
    }

    /**
     * Apaga todos os veiculos
     *
     */
    public void removeTodosVeiculos(){
        this.veiculos.clear();
    }

    /**
     * Apaga base de dados toda
     *
     */
    public void apagarTudo(){
        this.utilizadores.clear();
        this.veiculos.clear();
        this.alugueres.clear();
        this.livres.clear();
        this.ocupados.clear();
    }

    /**
     * Faz set de utilizadores
     *
     */
    public void setUtilizadores(Map<String, Utilizador> utili) {
        Iterator<Map.Entry<String, Utilizador>> uti = utili.entrySet().iterator();
        while(uti.hasNext()) {
            this.utilizadores.put(uti.next().getKey(), uti.next().getValue().clone());
        }
    }

    /**
     * Faz set de veiculos
     *
     */
    public void setVeiculos(Map<String, Veiculo> veiculos) {
        Iterator<Map.Entry<String, Veiculo>> uti = veiculos.entrySet().iterator();
        while(uti.hasNext()){
            this.veiculos.put(uti.next().getKey(), uti.next().getValue().clone());
        }
    }

//    -----------------
//    Guardar basedados
//    -----------------

    public static UMCarroJa carregaObjetoInformacao() throws IOException, ClassNotFoundException, FileNotFoundException
    {
        FileInputStream carregaFicheiro = new FileInputStream("basedados");
        if (carregaFicheiro == null) {
            throw new FileNotFoundException("");
        } else {
            ObjectInputStream obj = new ObjectInputStream(carregaFicheiro);
            if (obj == null) {
                throw new IOException("");
            } else {
                UMCarroJa p = (UMCarroJa) obj.readObject();
                if (p == null) {
                    throw new ClassNotFoundException("");
                } else {
                    obj.close();
                    return p;
                }
            }
        }
    }

    /**
     * Guarda num ficheiro um objecto da classe Informacao (esse ficheiro tem sempre o nome BaseDados)
     */
    public void gravaObjetoInformacao() throws IOException, FileNotFoundException
    {
        FileOutputStream guardaFicheiro = new FileOutputStream("basedados");
        if (guardaFicheiro == null) {
            throw new FileNotFoundException("");
        } else {
            ObjectOutputStream objeto = new ObjectOutputStream(guardaFicheiro);
            if (objeto == null) {
                throw new IOException("");
            } else {
                objeto.writeObject(this);
                objeto.flush();
                objeto.close();
            }
        }
    }

    /**
     * Imprime para um ficheiro os elementos da base de dados
     *
     * @param nomeFicheiro
     * @param opcao
     * @throws IOException
     */
    public void escreveEmFicheiroTxt(String nomeFicheiro, Integer opcao) throws IOException {
        PrintWriter fich = new PrintWriter(nomeFicheiro);
        switch(opcao){
            case(1):
                Iterator<Map.Entry<String, Utilizador>> cli = this.utilizadores.entrySet().iterator();
                while(cli.hasNext()){
                    fich.println(cli.next().getValue().toString());
                }
                break;
            case(2):
                Iterator<Map.Entry<String, Veiculo>> veic = this.veiculos.entrySet().iterator();
                while(veic.hasNext()){
                    fich.println(veic.next().getValue().toString());
                }
                break;
        }
        fich.flush();
        fich.close();
    }

    //--------------
    //OPCOES Cliente
    //--------------

    /**
     * Da o veiculo mais proximo das coordenadas do cliente
     *
     * @return veiculo
     */
    public Veiculo veiculoMaisProximo(Coordenada coord){
        if(this.livres.size() == 0) return null;
        Veiculo v = this.veiculos.get(this.livres.get(0));
        for(String s : this.livres){
            if(this.veiculos.get(s).getCoord().distancia(coord) < v.getCoord().distancia(coord)){
                v = this.veiculos.get(s);
            }
        }
        return v;
    }

    /**
     * Da o veiculo cujo preco por km e consumo sao menores
     *
     * @return veiculo
     */
    public Veiculo veiculoMaisBarato(){
        if(this.livres.size() == 0) return null;
        Veiculo v = this.veiculos.get(this.livres.get(0)).clone();
        for(String s : this.livres){
            if((this.veiculos.get(s).getPrecokm()*this.veiculos.get(s).getConsumo()) < (v.getPrecokm()*v.getConsumo())){
                v = this.veiculos.get(s);
            }
        }
        return v;
    }

    /**
     * Da o veiculo com a matriculo especificada
     *
     * @return veiculo
     */
    public Veiculo veiculoMaisBaratoPe(Coordenada coord){
        if(this.livres.size() == 0) return null;
        for(String s : this.livres){
            if(this.veiculos.get(s).getCoord().distancia(coord) < 2){
                return this.veiculos.get(s);
            }
        }
        return null;
    }

    /**
     * Da o veiculo com a matriculo especificada
     *
     * @return veiculo
     */
    public Veiculo veiculoEspecifico() throws Exception{
        if(this.livres.size() == 0 ) return null;
        Scanner input = new Scanner(System.in);
        System.out.println("Matricula do veiculo especifico: ");
        String matricula = input.next();
        if (this.livres.contains(matricula)) {
            return this.veiculos.get(matricula);
        }
        else{
            Veiculo v = veiculoEspecifico();
            return v;
        }
    }

    /**
     * Da a lista de alugueres de um cliente
     *
     * @return Lista de alugueres
     */
    public List<Aluguer> listaAlugueresCliente (String c){
        List<Aluguer> al = new ArrayList<Aluguer>();
        for(Integer i : this.histCli.get(c)) {
            al.add(this.alugueres.get(i));
        }
        System.out.println(al.size());
        return al;
    }

    /**
     * Da o veiculo com a autonomia desejada
     *
     * @return veiculo
     */
    public Veiculo veiculoAutonomiaDesejada(){
        if(this.livres.size() == 0) return null;
        Scanner input = new Scanner(System.in);
        System.out.println("Autonomia desejada: ");
        int autonomia = input.nextInt();
        for(String s : this.livres){
            if(this.veiculos.get(s).getAutonomia() == autonomia) {
                return this.veiculos.get(s);
            }
        }
        Veiculo v = veiculoAutonomiaDesejada();
        return v;
    }

    //-------------------
    //OPCOES PROPRIETARIO
    //-------------------

    /**
     * Retorna a lista de carros de um proprietario
     *
     * @param u proprietario
     */
    public List<Veiculo> listaCarros(Proprietario u){
        List<Veiculo> veicProp = new ArrayList<Veiculo>();
        Iterator<Map.Entry<String, Veiculo>> it = this.veiculos.entrySet().iterator();
        while(it.hasNext()){
            if(it.next().getValue().getProprietario().equals(u.getEmail())){
                veicProp.add(it.next().getValue());
            }
        }
        return veicProp;
    }

    /**
     * Diz se veiculo esta disponivel ou no caso de nao existir diz que e inexistente
     *
     * @param u proprietario
     */
    public void veiculoDisponivel(Proprietario u) throws Exception{
        Scanner input = new Scanner(System.in);
        System.out.println("Matricula do veiculo: ");
        String matricula = input.next();
        if(veiculos.containsKey(matricula) && this.veiculos.get(matricula).getProprietario().equals(u.getEmail())){
            this.livres.add(this.veiculos.get(matricula).getMatricula());
            System.out.println("Veiculo com matricula " + matricula + " está livre");
        }
        else System.out.println("Veiculo inexistente ou nao tem permissao para alterar a disponibilidade destes veiculo\n");
    }

    /**
     * Diz a classificacao de um proprietario
     *
     * @param u proprietario
     */
    public void verClassificacao(Proprietario u){
        System.out.println("Classificacao: " + u.getClassificacao());
    }

    /**
     * Abastece um veiculo com uma determinada percentagem
     *
     * @param u proprietario
     */
    public void abastecerVeiculo(Proprietario u){
        Scanner input = new Scanner(System.in);
        System.out.println("Matricula do veiculo: ");
        String matricula = input.next();

        Veiculo v = this.veiculos.get(matricula);

        if(this.veiculos.get(matricula).getAutonomia() == 100 || !v.getProprietario().equals(u.getEmail())){
            System.out.println("Deposito cheio ou nao tem permissao para abastecer este veiculo");
        }
        else {
            System.out.println("\nO veiculo tem autonomia:" + v.getAutonomia());
            System.out.println("\nAte que percentagem de deposito deseja encher(0 a 100): ");
            int percentagem = input.nextInt();

            if(percentagem < v.getAutonomia()){
                System.out.println("Autonomia tem de sair maior que a que o veiculo tem");
                percentagem = input.nextInt();
            }

            v.setAutonomia(percentagem);

            System.out.println("Veiculo de matricula " + matricula + " agora tem " + percentagem + " de autonomia");
        }
    }

    /**
     * Se autonomia menor que 10 retorna 1 s enao retorn 0
     *
     * @param u Proprietario
     */
    public Collection<Veiculo> listaVeiculosProp (Proprietario u){
        List<Veiculo> car = new ArrayList<Veiculo>();
        for(String s : this.listVeicProp.get(u.getEmail())){
            car.add(this.veiculos.get(s));
        }
        return car;
    }

    /**
     * Se autonomia menor que 10 retorna 1 s enao retorn 0
     *
     * @param v veiculo
     * @param coordI coordenada inicial
     * @param coordF coordenada final
     */
    public int temAutonomia(Cliente u, Veiculo v, Coordenada coordI, Coordenada coordF){
        Coordenada coordV = v.getCoord();
        double distIC = coordI.distancia(coordV);
        double distIF = coordI.distancia(coordF);
        if((distIC + distIF) > (v.getCapacidadeDeposito() * 100) / v.getConsumo()){
            return 0;
        }
        return 1;
    }

    public void adicionarVeiculo(Proprietario u){
        Scanner input = new Scanner(System.in);
        System.out.println("Matricula: ");
        String matricula = input.next();

        System.out.println("Velocidade media por km: ");
        double velmedkm = input.nextDouble();

        System.out.println("Preco por km: ");
        double precokm = input.nextDouble();

        System.out.println("Consumo: ");
        double consumo = input.nextDouble();
        int classificacao = 0;

        System.out.println("Autonomia (0 a 100): ");
        int autonomia = input.nextInt();

        System.out.println("Capacidade do deposito: ");
        int capacidadeDeposito = input.nextInt();

        System.out.println("Indique as coordenadas do lugar que se encontra: ");
        System.out.println("X: ");
        int coordX = input.nextInt();
        System.out.println("Y: ");
        int coordY = input.nextInt();
        Coordenada coord = new Coordenada(coordX, coordY);

        String prop = u.getEmail();

        System.out.println("Tipo de veiculo: ");
        Menu.tipoVeiculo();
        int x = input.nextInt();

        String estado = "livre";

        int soma = 0;

        int contTotal = 0;

        while(x!=1 && x!=2 && x!=3){
            System.out.println("Tipo incorreto");
        }

        List<Integer> m = new ArrayList<Integer>();

        switch (x) {
            case (1):
                Gasolina gas = new Gasolina(matricula, velmedkm, precokm, consumo, classificacao, soma, contTotal, coord, autonomia, capacidadeDeposito, prop);
                adicionaVeiculo(gas.clone());
                this.listVeicProp.get(prop).add(matricula);
                adicionaHistVeic(matricula, m);
                break;

            case(2):
                Veiculo hib = new Hibrido(matricula, velmedkm, precokm, consumo, classificacao, soma, contTotal, coord, autonomia, capacidadeDeposito, prop);
                adicionaVeiculo(hib.clone());
                this.listVeicProp.get(prop).add(matricula);
                adicionaHistVeic(matricula, m);
                break;

            case(3):
                Eletrico ele = new Eletrico(matricula, velmedkm, precokm, consumo, classificacao, soma, contTotal, coord, autonomia, capacidadeDeposito, prop);
                adicionaVeiculo(ele.clone());
                this.listVeicProp.get(prop).add(matricula);
                adicionaHistVeic(matricula, m);
                break;
        }
    }

    /**
     * Da a lista de alugueres de um Proprietario
     *
     * @param c Cliente
     * @return Lista de alugueres
     */
    public List<Aluguer> listaAlugueresProprietario (String c){
        List<Aluguer> al = new ArrayList<Aluguer>();
        for(Integer i : this.histProp.get(c)) {
            al.add(this.alugueres.get(i));
        }
        return al;
    }

    /**
     * Da a lista de alugueres de um veiculo
     *
     * @param matricula Matricula
     * @return Lista de alugueres
     */
    public List<Aluguer> listaAlugueresVeiculo (String matricula){
        List<Aluguer> al = new ArrayList<Aluguer>();
        for(Integer i : this.histVeic.get(matricula)) {
            al.add(this.alugueres.get(i));
        }
        return al;
    }

    //------------
    //OPCOES ADMIN
    //------------

    /**
     * Retorna a lista de utilizadores
     *
     */
    public Collection<Utilizador> listaUtilizadores(){
        return this.getUtilizadores().values();
    }

    /**
     * Retorna a lista de todos veiculos
     *
     */
    public Collection<Veiculo> listaVeiculos(){
        return this.getVeiculos().values();
    }

    /**
     * Retorna a lista de veiculos livres
     *
     */
    public List<Veiculo> listaLivres(){
        List<Veiculo> liv = new ArrayList<Veiculo>();
        for(String s : this.livres){
            liv.add(this.veiculos.get(s));
        }
        return liv;
    }

    /**
     * Retorna a lista de veiculos ocupados
     *
     */
    public List<Veiculo> listaOcupados(){
        List<Veiculo> ocu = new ArrayList<Veiculo>();
        for(String s : this.ocupados){
            ocu.add(this.veiculos.get(s));
        }
        return ocu;
    }

    /**
     * Clone da class UMCarroJa
     *
     */
    public UMCarroJa clone(){
        return new UMCarroJa(this);
    }
}
