/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean.admsbean;

import entidade.AdmsPaciente;
import entidade.GrlComuna;
import entidade.GrlConjugePessoa;
import entidade.GrlContacto;
import entidade.GrlDocumentoIdentificacao;
import entidade.GrlEndereco;
import entidade.GrlEstadoCivil;
import entidade.GrlFicheiroAnexado;
import entidade.GrlMunicipio;
import entidade.GrlPais;
import entidade.GrlPessoa;
import entidade.GrlProvincia;
import entidade.GrlReligiao;
import entidade.GrlSexo;
import entidade.GrlTipoDocumentoIdentificacao;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import sessao.AdmsPacienteFacade;
import sessao.GrlComunaFacade;
import sessao.GrlContactoFacade;
import sessao.GrlDocumentoIdentificacaoFacade;
import sessao.GrlEnderecoFacade;
import sessao.GrlMunicipioFacade;
import sessao.GrlPaisFacade;
import sessao.GrlPessoaFacade;
import sessao.GrlProvinciaFacade;
import sessao.GrlTipoDocumentoIdentificacaoFacade;

/**
 *
 * @author gemix
 */
@ManagedBean
@SessionScoped
@TransactionManagement(value=TransactionManagementType.BEAN)
public class PacienteBean
{
    @EJB
    private GrlComunaFacade grlComunaFacade;
    @EJB
    private GrlDocumentoIdentificacaoFacade grlDocumentoIdentificacaoFacade;
    @EJB
    private GrlEnderecoFacade grlEnderecoFacade;
    @EJB
    private GrlContactoFacade grlContactoFacade;
    @EJB
    private GrlPessoaFacade grlPessoaFacade;
//    @EJB
//    private GrlTipoDocumentoIdentificacaoFacade grlTipoDocumentoIdentificacaoFacade;
    
    @EJB
    private GrlMunicipioFacade grlMunicipioFacade;
    @EJB
    private GrlProvinciaFacade grlProvinciaFacade;
    @EJB
    private GrlPaisFacade grlPaisFacade;
    @EJB
    private AdmsPacienteFacade admsPacienteFacade;
    
    

    @Resource
    private UserTransaction userTransaction;
   

    private AdmsPaciente paciente;
    
    private GrlPessoa pessoa;
    
    private GrlEndereco endereco;
    
    private GrlPais pais;
    
    private GrlContacto contacto;
    
    private GrlDocumentoIdentificacao documentoIdenficacao;
    
    private int codigoPais;
    
    private GrlProvincia provincia;
    
    private int codigoProvincia;
    
    private int codigoMunicipio;
    
    private int codigoComuna;

    private int foto;
    
    private int codigoTipoDocumentoIdentificacao;
    
    private int codigoSexo;
    
    private int codigoReligiao;
    
    private int codigoNacionalidade;
    
    private int codigoEstadoCivil;
    
    
    
    /* outros  */
    //private boolean primeiraVez = true;
    private boolean alteraValorComboPais = true;
    private boolean alteraValorComboProvincia = true;
    private boolean alteraValorComboMunicipio = true;
    
    
    public PacienteBean()
    {
        documentoIdenficacao = new GrlDocumentoIdentificacao();
        contacto = new GrlContacto();
        provincia = new GrlProvincia();
        provincia.setFkIdPais(pais);
        pais = new GrlPais();
        endereco = new GrlEndereco();
        pessoa = new GrlPessoa();
        paciente = new AdmsPaciente();
    }
    

    
    public void setNomeCompleto(String nomeCompleto)
    {
        pessoa.setNomeCompleto(nomeCompleto);
    }
    
    public String getNomeCompleto()
    {
        return pessoa.getNomeCompleto();
    }
    
    public void setDataNascimento(Date dataNascimento)
    {
        pessoa.setDataNascimento(dataNascimento);
    }
    
    public Date getDataNascimento()
    {
        return pessoa.getDataNascimento();
    }
    
    
    public void setNomePai(String nomePai)
    {
        pessoa.setNomePai(nomePai);
    }
    
    public String getNomePai()
    {
        return pessoa.getNomePai();
    }
    

    public void setNomeMae(String nomeMae)
    {
        pessoa.setNomeMae(nomeMae);
    }
    
    public String getNomeMae()
    {
        return pessoa.getNomeMae();
    } 
    
    
    public void setSexo(int codigoSexo)
    {
        this.codigoSexo = codigoSexo;
    }
    
    public int getSexo()
    {
        return codigoSexo;
    }
    
    public int getTipoDocumentoIdentificacao()
    {
        return this.codigoTipoDocumentoIdentificacao;
    }

    public void setTipoDocumentoIdentificacao(int tipoDocumentoIdentificacao)
    {
        this.codigoTipoDocumentoIdentificacao = tipoDocumentoIdentificacao;
    }
    
    public void setDocumentoIdentificacao(String documentoIdentificacao)
    {
        documentoIdenficacao.setNumeroDocumento(documentoIdentificacao);
    }

    public String getDocumentoIdentificacao()
    {
        return documentoIdenficacao.getNumeroDocumento();
    }
    
    public void setReligiao(int codigoReligiao)
    {
        this.codigoReligiao = codigoReligiao;
    }
    
    public int getReligiao()
    {
        return codigoReligiao;
    }
    
    public void setNacionalidade(int codigoNacionalidade)
    {
        this.codigoNacionalidade = codigoNacionalidade;
    }
    
    public int getNacionalidade()
    {
        return this.codigoNacionalidade;
    }

//    public void setFoto(int foto)
//    {
//        this.foto = foto;
//    }
//    
//    public int getFoto()
//    {
//        return foto;
//    }
    
    public void setEstadoCivil(int codigoEstadoCivil)
    {
        this.codigoEstadoCivil = codigoEstadoCivil;
    }
    
    public int getEstadoCivil()
    {
        return this.codigoEstadoCivil;
    }
    
    
    
    public String getTelefone1()
    {
        return contacto.getTelefone1();
    }

    public void setTelefone1(String telefone1)
    {
        this.contacto.setTelefone1(telefone1);
    }
    
    public String getTelefone2()
    {
        return contacto.getTelefone2();
    }

    public void setTelefone2(String telefone2)
    {
        this.contacto.setTelefone2(telefone2);
    }
    
    public String getEmail1()
    {
        return this.contacto.getEmail1();
    }

    public void setEmail1(String email1)
    {
        this.contacto.setEmail1(email1);
    }
    
    
    public String getEmail2()
    {
        return contacto.getEmail2();
    }

    public void setEmail2(String email2)
    {
        this.contacto.setEmail2(email2);
    }
//    
    
    
    
    /***********************                Conjunge            ****************************
     
    
    
    
//    public void setNomeConjugePessoa(String nomeConjunge)
//    {
//        this.paciente.getFkIdPessoa().getFkIdConjugePessoa().setNomeConjuge(nomeConjunge);
//    }
//    
//    public String getNomeConjugePessoa()
//    {
//        return this.paciente.getFkIdPessoa().getFkIdConjugePessoa().getNomeConjuge();
//    }
//    
//    public void setDataNascConjugePessoa(Date nascConjugePessoa)
//    {
//        this.paciente.getFkIdPessoa().getFkIdConjugePessoa().setDataNascimento(nascConjugePessoa);
//    }
//    
//    public Date getDataNascConjugePessoa()
//    {
//        return this.paciente.getFkIdPessoa().getFkIdConjugePessoa().getDataNascimento();*/
//    }
    
    
    
    
    /***********************                Endereco
     * @param rua ****************************/
   
    
    public void setRua(String rua)
    {
        endereco.setRua(rua);
    }
  
    public String getRua()
    {
        return this.endereco.getRua();
    }
    
    
    public void setBairro(String bairro)
    {
        endereco.setBairro(bairro);
    }
  
    public String getBairro()
    {
        return this.endereco.getBairro();
    }
    
    
    public void setNumeroCasa(String numeroCasa)
    {
        endereco.setNumeroCasa(numeroCasa);
    }
    
    
    public String getNumeroCasa()
    {
        return this.endereco.getNumeroCasa();
    }
    
    public void setPais(int pais)
    {
        this.codigoPais = pais;
        alteraValorComboProvincia = true;
        alteraValorComboMunicipio = true;
    }
    
    public int getPais()
    {
        return this.codigoPais;
    }
    
    public void setProvincia(int provincia)
    {
        this.codigoProvincia = provincia;
        alteraValorComboMunicipio = true;
    }
    
    public int getProvincia()
    {
        return this.codigoProvincia;
    }
    
    public void setMunicipio(int codigoMunicipio)
    {
        this.codigoMunicipio = codigoMunicipio;
        System.err.println("Passou Aqui");
    }
    
    
    public int getMunicipio()
    {
        return this.codigoMunicipio;
    }
    
    public void setComuna(int codigoComuna)
    {
        this.codigoComuna = codigoComuna;
        System.err.println("Passou Aqui");
    }
    
    
    public int getComuna()
    {
        return this.codigoComuna;
    }
    

  
    
    
    /***********************         Gravar Paises        ****************************
     * @return  */
    
    public List<GrlPais> getPaises()
    {
        List<GrlPais> paises;
        
        paises = grlPaisFacade.findAll();
        
        if (alteraValorComboPais)
        {
            this.codigoPais = paises.get(0).getPkIdPais();
        }
        alteraValorComboPais = false;
        
        //System.err.println("Passou pais "+paises.size()+" codigo Pais "+this.codigoPais);
        
        return paises;
    }
    
    
    public List<GrlProvincia> getProvinciasPais()
    {
        List<GrlProvincia> provincias;
        
        provincias = grlProvinciaFacade.findProvinciaPais(codigoPais);
        
        if (alteraValorComboProvincia)
        {
            this.codigoProvincia = provincias.get(0).getPkIdProvincia();
        }
        alteraValorComboProvincia = false;
        
        //System.err.println("Passou provincia "+provincias.size()+" codigo Pais "+this.codigoPais);
        
        return provincias;
    }
    
    public List<GrlMunicipio> getMunicipiosProvincia()
    {
        List<GrlMunicipio> municipios;
        
        municipios = grlMunicipioFacade.findMunicipioProvincia(this.codigoProvincia);
        
        if (alteraValorComboMunicipio)
        {
            this.codigoMunicipio = municipios.get(0).getPkIdMunicipio();
        }
        alteraValorComboMunicipio = false;
                
        //System.err.println("Passou municipio "+municipios.size()+" codigo provincia "+this.codigoProvincia);
        
        return municipios;
    }
    
    public List<GrlComuna> getComunasMunicipio()
    {
        List<GrlComuna> comunas;
        
        comunas = grlComunaFacade.findComunaMunicipio(this.codigoMunicipio);
                
        //System.err.println("Passou "+comunas.size()+" codigo municipio "+this.codigoMunicipio);
        
        return comunas;
    }
    
    
    
    /***********************         Gravar Paciente        **************************** */
    
    public void gravarPaciente()
    {
        try
        {
            userTransaction.begin();
            
            documentoIdenficacao.setFkTipoDocumentoIdentificacao(new GrlTipoDocumentoIdentificacao(codigoTipoDocumentoIdentificacao));
            
            endereco.setFkIdComuna(new GrlComuna(codigoComuna));
            
            grlDocumentoIdentificacaoFacade.create(documentoIdenficacao);
            grlContactoFacade.create(contacto);
            grlEnderecoFacade.create(endereco);
            
            
            pessoa.setFkIdSexo(new GrlSexo(codigoSexo));
            pessoa.setFkIdContacto(contacto);
            pessoa.setFkIdDocumentoIdentificacao(documentoIdenficacao);
            //pessoa.setFkIdFoto(new GrlFicheiroAnexado());
            pessoa.setFkIdNacionalidade(new GrlPais(codigoNacionalidade));
            pessoa.setFkIdEndereco(endereco);
            pessoa.setFkIdEstadoCivil(new GrlEstadoCivil(codigoEstadoCivil));
            pessoa.setFkIdReligiao(new GrlReligiao(codigoReligiao));
            
            grlPessoaFacade.create(pessoa);
            
            paciente.setFkIdPessoa(pessoa);
            paciente.setNumeroPaciente("P"+pessoa.getPkIdPessoa());

            admsPacienteFacade.create(paciente);

            userTransaction.commit();
            
        }
        catch (RollbackException ex)
        {
            Logger.getLogger(PacienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (HeuristicMixedException ex)
        {
            Logger.getLogger(PacienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (HeuristicRollbackException ex)
        {
            Logger.getLogger(PacienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SecurityException ex)
        {
            Logger.getLogger(PacienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalStateException ex)
        {
            Logger.getLogger(PacienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NotSupportedException ex)
        {
            Logger.getLogger(PacienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SystemException ex)
        {
            Logger.getLogger(PacienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
 
    
}
