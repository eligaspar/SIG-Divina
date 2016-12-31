/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entidade.GrlComuna;
import entidade.GrlEspecialidade;
import entidade.GrlEstadoCivil;
import entidade.GrlInstituicao;
import entidade.GrlMunicipio;
import entidade.GrlPais;
import entidade.GrlProvincia;
import entidade.GrlReligiao;
import entidade.GrlSexo;
import entidade.GrlTipoDocumentoIdentificacao;
import entidade.RhCargo;
import entidade.RhCategoriaProfissao;
import entidade.RhCurso;
import entidade.RhDepartamento;
import entidade.RhFaculdade;
import entidade.RhProfissao;
import entidade.RhSeccaoTrabalho;
import entidade.RhTipoCandidatura;
import entidade.RhTipoFalta;
import entidade.RhTipoFuncionario;
import entidade.RhTipoLicenca;
import entidade.RhUniversidade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import sessao.GrlComunaFacade;
import sessao.GrlEspecialidadeFacade;
import sessao.GrlEstadoCivilFacade;
import sessao.GrlInstituicaoFacade;
import sessao.GrlMunicipioFacade;
import sessao.GrlPaisFacade;
import sessao.GrlProvinciaFacade;
import sessao.GrlReligiaoFacade;
import sessao.GrlSexoFacade;
import sessao.GrlTipoDocumentoIdentificacaoFacade;
import sessao.RhCargoFacade;
import sessao.RhCategoriaProfissaoFacade;
import sessao.RhCursoFacade;
import sessao.RhDepartamentoFacade;
import sessao.RhFaculdadeFacade;
import sessao.RhProfissaoFacade;
import sessao.RhSeccaoTrabalhoFacade;
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
public class ItensAjaxBean
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
    private GrlComunaFacade comunaFacade;
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
    private RhDepartamentoFacade departamentoFacade;
    @EJB
    private RhSeccaoTrabalhoFacade seccaoTrabalhoFacade;
    @EJB
    private RhCargoFacade cargoFacade;
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


    private List<GrlTipoDocumentoIdentificacao> tipoDocumentoIdentificacaoList;
    private List<GrlSexo> sexoList;
    private List<GrlReligiao> religiaoList;
    private List<GrlPais> paisList;
    private List<GrlProvincia> provinciaList;
    private List<GrlMunicipio> municipioList;
    private List<GrlComuna> comunaList;
    private List<GrlEstadoCivil> estadoCivilList;
    private List<GrlInstituicao> instituicaoList;
    private List<RhUniversidade> universidadeList;
    private List<RhFaculdade> faculdadeList;
    private List<RhCurso> cursoList;
    private List<RhTipoCandidatura> tipoCandidaturaList;
    private List<RhTipoFuncionario> tipoFuncionarioList;
    private List<RhDepartamento> departamentoList;
    private List<RhSeccaoTrabalho> seccaoTrabalhoList;
    private List<RhCargo> cargoList;
    private List<RhProfissao> profissaoList;
    private List<RhCategoriaProfissao> categoriaProfissaoList;
    private List<GrlEspecialidade> especialidadeList;
    private List<RhTipoFalta> tipoFaltaList;
    private List<RhTipoLicenca> tipoLicencaList;
    
    /**
     * Creates a new instance of ItensSelectGerais
     */
    public ItensAjaxBean ()
    {
    }
    
    public void changeTipoFuncionario(ValueChangeEvent e)
    {
        tipoFuncionario = Integer.parseInt(e.getNewValue().toString());
    }
    
    public void changePais(ValueChangeEvent e)
    {
        pais = Integer.parseInt(e.getNewValue().toString());
        provincia = municipio = null;
        actualizarProvincias(Integer.parseInt(e.getNewValue().toString()));
    }
    
    public void changeProvincia(ValueChangeEvent e)
    {
        provincia = Integer.parseInt(e.getNewValue().toString());
        municipio = null;
        actualizarMunicipios(Integer.parseInt(e.getNewValue().toString()));
    }

    public void changeMunicipio(ValueChangeEvent e)
    {
        municipio = Integer.parseInt(e.getNewValue().toString());
        actualizarComunas(Integer.parseInt(e.getNewValue().toString()));
    }

    public void changeDepartamento(ValueChangeEvent e)
    {
        departamento = Integer.parseInt(e.getNewValue().toString());
        seccaoTrabalho = null;
        actualizarSeccoesTrabalho(Integer.parseInt(e.getNewValue().toString()));
    }
    
    public void changeSeccaoTrabalho(ValueChangeEvent e)
    {
        seccaoTrabalho = Integer.parseInt(e.getNewValue().toString());
        actualizarCargos(Integer.parseInt(e.getNewValue().toString()));
    }
    
    public void changeProfissao(ValueChangeEvent e)
    {
        profissao = Integer.parseInt(e.getNewValue().toString());
        actualizarCategoriasProfissao(Integer.parseInt(e.getNewValue().toString()));
        actualizarEspecialidades(Integer.parseInt(e.getNewValue().toString()));
    }
    
    private void actualizarProvincias(Integer idPais)
    {
        provinciaList = provinciaFacade.pesquisaPorPais(idPais);
    }
    private void actualizarMunicipios(Integer idProvincia)
    {
        municipioList = municipioFacade.pesquisaPorProvincia(idProvincia);
    }
    
    private void actualizarComunas(Integer idMunicipio)
    {
        comunaList = comunaFacade.pesquisaPorMunicipio(idMunicipio);
    }
    
    private void actualizarCursos(Integer idFaculdade)
    {
        cursoList = null;
        cursoList = cursoFacade.pesquisaPorFaculdade(idFaculdade);
    }
    
    private void actualizarSeccoesTrabalho(Integer idDepartamento)
    {
        seccaoTrabalhoList = null;
        seccaoTrabalhoList = seccaoTrabalhoFacade.pesquisaPorDepartamento(idDepartamento);
    }
    
    private void actualizarCargos(Integer idSeccao)
    {
        cargoList = null;
        cargoList = cargoFacade.pesquisaPorSeccao(idSeccao);
    }
    
    private void actualizarCategoriasProfissao(Integer idProfissao)
    {
        categoriaProfissaoList = null;
        categoriaProfissaoList = categoriaProfissaoFacade.pesquisaPorProfissao(idProfissao);
    }
    
    private void actualizarEspecialidades(Integer idProfissao)
    {
        especialidadeList = null;
        especialidadeList = especialidadeFacade.pesquisaPorProfissao(idProfissao);
    }

    public List<GrlTipoDocumentoIdentificacao> getTipoDocumentoIdentificacaoList ()
    {
        tipoDocumentoIdentificacaoList = tipoDocumentoIdentificacaoFacade.findAll();
        return tipoDocumentoIdentificacaoList;
    }

    public List<GrlSexo> getSexoList ()
    {
        sexoList = sexoFacade.findAll();
        return sexoList;
    }

    public List<GrlReligiao> getReligiaoList ()
    {
        religiaoList = religiaoFacade.findAll();
        return religiaoList;
    }

    public List<GrlPais> getPaisList ()
    {
        paisList = paisFacade.findAll();

        actualizarProvincias(null);
        if(! paisList.isEmpty())
        {
            if(pais == null)
                pais = paisList.get(0).getPkIdPais();
            actualizarProvincias(pais);
        }
        return paisList;
    }

    public List<GrlProvincia> getProvinciaList ()
    {
        actualizarMunicipios(null);
        if (provinciaList != null)
            if (! provinciaList.isEmpty())
            {
                if(provincia == null)
                    provincia = provinciaList.get(0).getPkIdProvincia();
                actualizarMunicipios(provincia);
            }
        return provinciaList;
    }

    public List<GrlMunicipio> getMunicipioList ()
    {
        actualizarComunas(null);
        if (municipioList != null)
            if (! municipioList.isEmpty())
            {
                if(municipio == null)
                    municipio = municipioList.get(0).getPkIdMunicipio();
                actualizarComunas(municipio);
            }
        return municipioList;
    }

    public List<GrlComuna> getComunaList ()
    {
        return comunaList;
    }

    public List<GrlEstadoCivil> getEstadoCivilList ()
    {
        estadoCivilList = estadoCivilFacade.findAll();
        return estadoCivilList;
    }

    public List<GrlInstituicao> getInstituicaoList ()
    {
        instituicaoList = instituicaoFacade.findAll();
        return instituicaoList;
    }

    public List<RhUniversidade> getUniversidadeList ()
    {
        universidadeList = universidadeFacade.findAll();
        return universidadeList;
    }

    public List<RhFaculdade> getFaculdadeList ()
    {
        faculdadeList = faculdadeFacade.findAll();

        actualizarCursos(null);
        if(! faculdadeList.isEmpty())
        {
            if(faculdade == null)
                faculdade = faculdadeList.get(0).getPkIdFaculdade();
            actualizarCursos(faculdade);
        }
        return faculdadeList;
    }

    public List<RhCurso> getCursoList ()
    {
        return cursoList;
    }

    public List<RhTipoCandidatura> getTipoCandidaturaList ()
    {
        tipoCandidaturaList = tipoCandidaturaFacade.findAll();
        return tipoCandidaturaList;
    }

    public List<RhTipoFuncionario> getTipoFuncionarioList ()
    {
        tipoFuncionarioList = tipoFuncionarioFacade.findAll();
        return tipoFuncionarioList;
    }

    public List<RhDepartamento> getDepartamentoList ()
    {
        departamentoList = departamentoFacade.findAll();
        
        actualizarSeccoesTrabalho(null);
        if(! departamentoList.isEmpty())
            actualizarSeccoesTrabalho(departamentoList.get(0).getPkIdDepartamento());
        
        return departamentoList;
    }

    public List<RhSeccaoTrabalho> getSeccaoTrabalhoList ()
    {
        actualizarCargos(null);
        if (seccaoTrabalhoList != null)
            if (! seccaoTrabalhoList.isEmpty())
            {
                if(seccaoTrabalho == null)
                    seccaoTrabalho = seccaoTrabalhoList.get(0).getPkIdSeccaoTrabalho();
                actualizarCargos(seccaoTrabalho);
            }
        return seccaoTrabalhoList;
    }

    public List<RhCargo> getCargoList ()
    {
        return cargoList;
    }

    public List<RhProfissao> getProfissaoList ()
    {
        profissaoList = profissaoFacade.findAll();
        
        actualizarCategoriasProfissao(null);
        actualizarEspecialidades(null);
        
        if (! profissaoList.isEmpty())
        {
            if(profissao == null)
                profissao = profissaoList.get(0).getPkIdProfissao();
            actualizarCategoriasProfissao(profissao);
            actualizarEspecialidades(profissao);
        }
        return profissaoList;
    }

    public List<RhCategoriaProfissao> getCategoriaProfissaoList ()
    {
        return categoriaProfissaoList;
    }

    public List<GrlEspecialidade> getEspecialidadeList ()
    {
        return especialidadeList;
    }

    public List<RhTipoFalta> getTipoFaltaList ()
    {
        tipoFaltaList = tipoFaltaFacade.findAll();
        return tipoFaltaList;
    }

    public List<RhTipoLicenca> getTipoLicencaList ()
    {
        tipoLicencaList = tipoLicencaFacade.findAll();
        return tipoLicencaList;
    }
    
    
    
    
    /**
     * 
     * Atributos para pegar valores de formulários
     * 
     */
    private Integer tipoFuncionario;
    private Integer pais;
    private Integer provincia;
    private Integer municipio;
    private Integer faculdade;
    private Integer departamento;
    private Integer seccaoTrabalho;
    private Integer profissao;

    public Integer getTipoFuncionario ()
    {
        return tipoFuncionario;
    }

    public void setTipoFuncionario (Integer tipoFuncionario)
    {
        this.tipoFuncionario = tipoFuncionario;
    }
    
    public RhUniversidadeFacade getUniversidadeFacade ()
    {
        return universidadeFacade;
    }

    public void setUniversidadeFacade (RhUniversidadeFacade universidadeFacade)
    {
        this.universidadeFacade = universidadeFacade;
    }


    public Integer getPais ()
    {
        return pais;
    }

    public void setPais (Integer pais)
    {
        this.pais = pais;
    }

    public Integer getProvincia ()
    {
        return provincia;
    }

    public void setProvincia (Integer provincia)
    {
        this.provincia = provincia;
    }

    public Integer getMunicipio ()
    {
        return municipio;
    }

    public void setMunicipio (Integer municipio)
    {
        this.municipio = municipio;
    }

    public Integer getFaculdade ()
    {
        return faculdade;
    }

    public void setFaculdade (Integer faculdade)
    {
        this.faculdade = faculdade;
    }

    public Integer getDepartamento ()
    {
        return departamento;
    }

    public void setDepartamento (Integer departamento)
    {
        this.departamento = departamento;
    }

    public Integer getSeccaoTrabalho ()
    {
        return seccaoTrabalho;
    }

    public void setSeccaoTrabalho (Integer seccaoTrabalho)
    {
        this.seccaoTrabalho = seccaoTrabalho;
    }

    public Integer getProfissao ()
    {
        return profissao;
    }

    public void setProfissao (Integer profissao)
    {
        this.profissao = profissao;
    }

    
}
