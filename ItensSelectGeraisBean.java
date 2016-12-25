/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entidade.GrlEspecialidade;
import entidade.GrlEstadoCivil;
import entidade.GrlInstituicao;
import entidade.GrlMunicipio;
import entidade.GrlPais;
import entidade.GrlProvincia;
import entidade.GrlReligiao;
import entidade.GrlSexo;
import entidade.GrlTipoDocumentoIdentificacao;
import entidade.RhCategoriaProfissao;
import entidade.RhCurso;
import entidade.RhFaculdade;
import entidade.RhProfissao;
import entidade.RhTipoCandidatura;
import entidade.RhTipoFalta;
import entidade.RhTipoFuncionario;
import entidade.RhTipoLicenca;
import entidade.RhUniversidade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import sessao.GrlEspecialidadeFacade;
import sessao.GrlEstadoCivilFacade;
import sessao.GrlInstituicaoFacade;
import sessao.GrlMunicipioFacade;
import sessao.GrlPaisFacade;
import sessao.GrlProvinciaFacade;
import sessao.GrlReligiaoFacade;
import sessao.GrlSexoFacade;
import sessao.GrlTipoDocumentoIdentificacaoFacade;
import sessao.RhCategoriaProfissaoFacade;
import sessao.RhCursoFacade;
import sessao.RhFaculdadeFacade;
import sessao.RhProfissaoFacade;
import sessao.RhTipoCandidaturaFacade;
import sessao.RhTipoFaltaFacade;
import sessao.RhTipoFuncionarioFacade;
import sessao.RhTipoLicencaFacade;
import sessao.RhUniversidadeFacade;

/**
 *
 * @author Ornela F. Boaventura
 */
@ManagedBean
@SessionScoped
public class ItensSelectGeraisBean
{

    @EJB
    private GrlTipoDocumentoIdentificacaoFacade tipoDocumentoIdentificacaoFacade;
    @EJB
    private GrlSexoFacade sexoFacade;
    @EJB
    private GrlReligiaoFacade religiaoFacade;
    @EJB
    private GrlPaisFacade paisFacade;
    @EJB
    private GrlProvinciaFacade provinciaFacade;
    @EJB
    private GrlMunicipioFacade municipioFacade;
    @EJB
    private GrlEstadoCivilFacade estadoCivilFacade;
    @EJB
    private GrlInstituicaoFacade instituicaoFacade;
    @EJB
    private RhUniversidadeFacade universidadeFacade;
    @EJB
    private RhFaculdadeFacade faculdadeFacade;
    @EJB
    private RhCursoFacade cursoFacade;
    @EJB
    private RhTipoCandidaturaFacade tipoCandidaturaFacade;
    @EJB
    private RhTipoFuncionarioFacade tipoFuncionarioFacade;
    @EJB
    private RhProfissaoFacade profissaoFacade;
    @EJB
    private RhCategoriaProfissaoFacade categoriaProfissaoFacade;
    @EJB
    private GrlEspecialidadeFacade especialidadeFacade;
    @EJB
    private RhTipoFaltaFacade tipoFaltaFacade;
    @EJB
    private RhTipoLicencaFacade tipoLicencaFacade;

    private String tipoDocumentoIdentificacao;
    private String sexo;
    private String religiao;
    private String pais;
    private String provincia;
    private String municipio;
    private String estadoCivil;
    private String instituicao;
    private String universidade;
    private List<String> universidadeLista;
    private String faculdade;
    private List<String> faculdadeLista;
    private String curso;
    private List<String> cursoLista;
    private String tipoCandidatura;
    private String tipoFuncionario;
    private String profissao;
    private String categoriaProfissao;
    private String especialidade;
    private List<String> especialidadeLista;
    private String tipoFalta;
    private String tipoLicenca;

    private ArrayList<SelectItem> itensMunicipio;
    
    /**
     * Creates a new instance of ItensSelectGerais
     */
    public ItensSelectGeraisBean ()
    {
    }

    public void changeEstadoCivil(ValueChangeEvent e)
    {
        estadoCivil = e.getNewValue().toString();
    }
    public void changeProvincia(ValueChangeEvent e)
    {
        actualizarMunicipios(Integer.parseInt(e.getNewValue().toString()));
    }
    
    private void actualizarMunicipios(int idProvincia)
    {
        itensMunicipio = null;
        itensMunicipio = new ArrayList<>();

        for (GrlMunicipio m : municipioFacade.pesquisaPorProvincia(idProvincia))
        {
            itensMunicipio.add(new SelectItem(m.getPkIdMunicipio(), m.getNomeMunicipio()));
        }
    }
    
    /**
     * Geração de itens para serem usados em combobox, lista e outros
     * @return
     */
    public ArrayList<SelectItem> getItensTipoDocumentoIdentificacao ()
    {
        ArrayList<SelectItem> itens = new ArrayList<>();

        for (GrlTipoDocumentoIdentificacao t : tipoDocumentoIdentificacaoFacade.findAll())
        {
            itens.add(new SelectItem(t.getPkTipoDocumentoIdentificacao(), t.getDescricao()));
        }
        return itens;
    }

    public ArrayList<SelectItem> getItensSexo ()
    {
        ArrayList<SelectItem> itens = new ArrayList<>();

        for (GrlSexo s : sexoFacade.findAll())
        {
            itens.add(new SelectItem(s.getPkIdSexo(), s.getDescricao()));
        }
        return itens;
    }

    public ArrayList<SelectItem> getItensReligiao ()
    {
        ArrayList<SelectItem> itens = new ArrayList<>();

        for (GrlReligiao r : religiaoFacade.findAll())
        {
            itens.add(new SelectItem(r.getPkIdReligiao(), r.getDescricao()));
        }
        return itens;
    }

    public ArrayList<SelectItem> getItensPais ()
    {
        ArrayList<SelectItem> itens = new ArrayList<>();

        for (GrlPais p : paisFacade.findAll())
        {
            itens.add(new SelectItem(p.getPkIdPais(), p.getNomePais()));
        }
        return itens;
    }

    public ArrayList<SelectItem> getItensProvincia ()
    {
        ArrayList<SelectItem> itens = new ArrayList<>();

        if (pais != null)
        {
            List<GrlProvincia> lista = provinciaFacade.pesquisaPorPais(Integer.parseInt(pais));
            for (GrlProvincia p : lista)
            {
                //Se for a primeira província na lista
                //Carrega os seus municípios para que sejam apresentados na combobox de municípios
                if (lista.indexOf(p) == 0)
                    actualizarMunicipios(p.getPkIdProvincia());
                itens.add(new SelectItem(p.getPkIdProvincia(), p.getNomeProvincia()));
            }
        }
        else if( ! paisFacade.findAll().isEmpty() )
        {
            pais = paisFacade.findAll().get(0).getPkIdPais().toString();
            itens = getItensProvincia();
        }
            
        return itens;
    }

    public ArrayList<SelectItem> getItensMunicipio ()
    {
        return itensMunicipio;
    }

    public ArrayList<SelectItem> getItensEstadoCivil ()
    {
        ArrayList<SelectItem> itens = new ArrayList<>();

        for (GrlEstadoCivil e : estadoCivilFacade.findAll())
        {
            itens.add(new SelectItem(e.getPkIdEstadoCivil(), e.getDescricao()));
        }
        return itens;
    }

    public ArrayList<SelectItem> getItensInstituicao ()
    {
        ArrayList<SelectItem> itens = new ArrayList<>();

        for (GrlInstituicao i : instituicaoFacade.findAll())
        {
            itens.add(new SelectItem(i.getPkIdInstituicao(), i.getDescricao()));
        }
        return itens;
    }

    public ArrayList<SelectItem> getItensUniversidade ()
    {
        ArrayList<SelectItem> itens = new ArrayList<>();

        for (RhUniversidade u : universidadeFacade.findAll())
        {
            itens.add(new SelectItem(u.getPkIdUniversidade(), u.getFkIdInstituicao().getDescricao()));
        }
        return itens;
    }

    public ArrayList<SelectItem> getItensFaculdade ()
    {
        ArrayList<SelectItem> itens = new ArrayList<>();

        for (RhFaculdade f : faculdadeFacade.findAll())
        {
            itens.add(new SelectItem(f.getPkIdFaculdade(), f.getDescricao()));
        }
        return itens;
    }

    public ArrayList<SelectItem> getItensCurso ()
    {
        ArrayList<SelectItem> itens = new ArrayList<>();

        if (faculdade != null)
        {
            for (RhCurso c : cursoFacade.pesquisaPorFaculdade(Integer.parseInt(faculdade)))
            {
                itens.add(new SelectItem(c.getPkIdCurso(), c.getDescricao()));
            }
        }

        return itens;
    }

    public ArrayList<SelectItem> getItensTipoCandidatura ()
    {
        ArrayList<SelectItem> itens = new ArrayList<>();

        for (RhTipoCandidatura t : tipoCandidaturaFacade.findAll())
        {
            itens.add(new SelectItem(t.getPkIdTipoCandidatura(), t.getDescricao()));
        }
        return itens;
    }

    public ArrayList<SelectItem> getItensTipoFuncionario ()
    {
        ArrayList<SelectItem> itens = new ArrayList<>();

        for (RhTipoFuncionario t : tipoFuncionarioFacade.findAll())
        {
            itens.add(new SelectItem(t.getPkIdTipoFuncionario(), t.getDescricao()));
        }
        return itens;
    }

    public ArrayList<SelectItem> getItensProfissao ()
    {
        ArrayList<SelectItem> itens = new ArrayList<>();

        for (RhProfissao p : profissaoFacade.findAll())
        {
            itens.add(new SelectItem(p.getPkIdProfissao(), p.getDescricao()));
        }
        return itens;
    }

    public ArrayList<SelectItem> getItensCategoriaProfissao ()
    {
        ArrayList<SelectItem> itens = new ArrayList<>();

        if (profissao != null)
        {
            for (RhCategoriaProfissao c : categoriaProfissaoFacade.pesquisaPorProfissao(Integer.parseInt(profissao)))
            {
                itens.add(new SelectItem(c.getPkIdCategoriaProfissao(), c.getDescricao()));
            }
        }

        return itens;
    }

    /**
     * Obtém uma lista de itens das especialidades
     *
     * @return
     */
    public ArrayList<SelectItem> getItensEspecialidade ()
    {
        ArrayList<SelectItem> itens = new ArrayList<>();

        if (profissao != null)
        {
            for (GrlEspecialidade e : especialidadeFacade.pesquisaPorProfissao(Integer.parseInt(profissao)))
            {
                itens.add(new SelectItem(e.getPkIdEspecialidade(), e.getDescricao()));
            }
        }
        return itens;
    }

    public ArrayList<SelectItem> getItensTipoFalta ()
    {
        ArrayList<SelectItem> itens = new ArrayList<>();

        for (RhTipoFalta t : tipoFaltaFacade.findAll())
        {
            itens.add(new SelectItem(t.getPkIdTipoFalta(), t.getDescricao()));
        }
        return itens;
    }

    public ArrayList<SelectItem> getItensTipoLicenca ()
    {
        ArrayList<SelectItem> itens = new ArrayList<>();

        for (RhTipoLicenca t : tipoLicencaFacade.findAll())
        {
            itens.add(new SelectItem(t.getPkIdTipoLicenca(), t.getDescricao()));
        }
        return itens;
    }

    /*
     * Métodos de Acesso e de alteração para as entidades
     */
    public String getTipoDocumentoIdentificacao ()
    {
        return tipoDocumentoIdentificacao;
    }

    public void setTipoDocumentoIdentificacao (String tipoDocumentoIdentificacao)
    {
        this.tipoDocumentoIdentificacao = tipoDocumentoIdentificacao;
    }

    public String getSexo ()
    {
        return sexo;
    }

    public void setSexo (String sexo)
    {
        this.sexo = sexo;
    }

    public String getReligiao ()
    {
        return religiao;
    }

    public void setReligiao (String religiao)
    {
        this.religiao = religiao;
    }

    public String getPais ()
    {
        return pais;
    }

    public void setPais (String pais)
    {
        this.pais = pais;
    }

    public String getProvincia ()
    {
        return provincia;
    }

    public void setProvincia (String provincia)
    {
        this.provincia = provincia;
    }

    public String getMunicipio ()
    {
        return municipio;
    }

    public void setMunicipio (String municipio)
    {
        this.municipio = municipio;
    }

    public String getEstadoCivil ()
    {
        return estadoCivil;
    }

    public void setEstadoCivil (String estadoCivil)
    {
        this.estadoCivil = estadoCivil;
    }

    public String getDescricaoEstadoCivil ()
    {
        try
        {
            if (estadoCivil != null)
                return estadoCivilFacade.find(Integer.parseInt(estadoCivil)).getDescricao();
        }
        catch (EJBException | NullPointerException | NumberFormatException e)
        {
            Mensagem.erroMsg(FacesContext.getCurrentInstance(), e.getMessage());
        }
        return null;
    }

    public GrlEstadoCivilFacade getEstadoCivilFacade ()
    {
        return estadoCivilFacade;
    }

    public void setEstadoCivilFacade (GrlEstadoCivilFacade estadoCivilFacade)
    {
        this.estadoCivilFacade = estadoCivilFacade;
    }

    public String getInstituicao ()
    {
        return instituicao;
    }

    public void setInstituicao (String instituicao)
    {
        this.instituicao = instituicao;
    }

    public String getUniversidade ()
    {
        return universidade;
    }

    public void setUniversidade (String universidade)
    {
        this.universidade = universidade;
    }

    public List<String> getUniversidadeLista ()
    {
        return universidadeLista;
    }

    public void setUniversidadeLista (List<String> universidadeLista)
    {
        this.universidadeLista = universidadeLista;
    }

    public String getFaculdade ()
    {
        return faculdade;
    }

    public void setFaculdade (String faculdade)
    {
        this.faculdade = faculdade;
    }

    public List<String> getFaculdadeLista ()
    {
        return faculdadeLista;
    }

    public void setFaculdadeLista (List<String> faculdadeLista)
    {
        this.faculdadeLista = faculdadeLista;
    }

    public String getCurso ()
    {
        return curso;
    }

    public void setCurso (String curso)
    {
        this.curso = curso;
    }

    public List<String> getCursoLista ()
    {
        return cursoLista;
    }

    public void setCursoLista (List<String> cursoLista)
    {
        this.cursoLista = cursoLista;
    }

    public String getTipoCandidatura ()
    {
        return tipoCandidatura;
    }

    public void setTipoCandidatura (String tipoCandidatura)
    {
        this.tipoCandidatura = tipoCandidatura;
    }

    public String getTipoFuncionario ()
    {
        return tipoFuncionario;
    }

    public void setTipoFuncionario (String tipoFuncionario)
    {
        this.tipoFuncionario = tipoFuncionario;
    }
    public String getDescricaoTipoFuncionario ()
    {
        try
        {
            if (tipoFuncionario != null)
                return tipoFuncionarioFacade.find(Integer.parseInt(tipoFuncionario)).getDescricao();
        }
        catch (EJBException | NullPointerException | NumberFormatException e)
        {
            Mensagem.erroMsg(FacesContext.getCurrentInstance(), e.getMessage());
        }
        return null;
    }

    public String getProfissao ()
    {
        return profissao;
    }

    public void setProfissao (String profissao)
    {
        this.profissao = profissao;
    }

    public String getCategoriaProfissao ()
    {
        return categoriaProfissao;
    }

    public void setCategoriaProfissao (String categoriaProfissao)
    {
        this.categoriaProfissao = categoriaProfissao;
    }

    public String getEspecialidade ()
    {
        return especialidade;
    }

    public void setEspecialidade (String especialidade)
    {
        this.especialidade = especialidade;
    }

    public List<String> getEspecialidadeLista ()
    {
        return especialidadeLista;
    }

    public void setEspecialidadeLista (List<String> especialidadeLista)
    {
        this.especialidadeLista = especialidadeLista;
    }

    public String getTipoFalta ()
    {
        return tipoFalta;
    }

    public void setTipoFalta (String tipoFalta)
    {
        this.tipoFalta = tipoFalta;
    }

    public String getTipoLicenca ()
    {
        return tipoLicenca;
    }

    public void setTipoLicenca (String tipoLicenca)
    {
        this.tipoLicenca = tipoLicenca;
    }

}
