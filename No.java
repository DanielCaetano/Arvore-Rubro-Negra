
public class No {

    private Integer chave;
    private No p, esq, dir;
    private boolean cor;

    public No(int n, boolean cor) {
        this.chave = n;
        this.cor = cor;
        this.p = this.esq = this.dir = ArvoreRN.nil;
    }

    public Integer getChave() {
        return chave;
    }

    public void setChave(Integer chave) {
        this.chave = chave;
    }

    public No getP() {
        return p;
    }

    public void setP(No p) {
        this.p = p;
    }

    public No getEsq() {
        return esq;
    }

    public void setEsq(No esq) {
        this.esq = esq;
    }

    public No getDir() {
        return dir;
    }

    public void setDir(No dir) {
        this.dir = dir;
    }

    public boolean getCor() {
        return cor;
    }

    public void setCor(boolean cor) {
        this.cor = cor;
    }
}
