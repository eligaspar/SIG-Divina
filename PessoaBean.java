/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean.grlbean;

import entidade.GrlComuna;
import entidade.GrlContacto;
import entidade.GrlDocumentoIdentificacao;
import entidade.GrlEndereco;
import entidade.GrlEstadoCivil;
import entidade.GrlFicheiroAnexado;
import entidade.GrlPais;
import entidade.GrlPessoa;
import entidade.GrlReligiao;
import entidade.GrlSexo;
import entidade.GrlTipoDocumentoIdentificacao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import sessao.GrlComunaFacade;
import sessao.GrlContactoFacade;
import sessao.GrlDocumentoIdentificacaoFacade;
import sessao.GrlEnderecoFacade;
import sessao.GrlFicheiroAnexadoFacade;
import sessao.GrlPessoaFacade;
import sessao.GrlTipoDocumentoIdentificacaoFacade;
import util.Constantes;
import util.ItensAjaxBean;
import util.Mensagem;
import util.UploadFicheiro;

/**
 *
 * @author Ornela F. Boaventura
 * @author Garcia Paulo
 */
@ManagedBean
@SessionScoped
public class PessoaBean
{

     FacesContext context = FacesContext.getCurrentInstance();

     @Resource
     private UserTransaction userTransaction;

     @EJB
     private GrlFicheiroAnexadoFacade ficheiroAnexadoFacade;
     @EJB
     private GrlContactoFacade contactoFacade;
     @EJB
     private GrlComunaFacade comunaFacade;
     @EJB
     private GrlEnderecoFacade enderecoFacade;
     @EJB
     private GrlTipoDocumentoIdentificacaoFacade grlTipoDocumentoIdentificacaoFacade;
     @EJB
     private GrlDocumentoIdentificacaoFacade documentoIdentificacaoFacade;
     @EJB
     private GrlPessoaFacade pessoaFacade;

     private boolean pesquisar;

     /**
      * Entidade pessoa
      */
     private GrlPessoa pessoa, pessoaPesquisa, pessoaVisualizar;
     private Part fotoCarregada;

     private int tipoDocumento;
     private String numeroDocumento;

     public PessoaBean ()
     {
     }

     public GrlPessoa getInstanciaPessoa ()
     {
          GrlFicheiroAnexado foto = new GrlFicheiroAnexado();
          foto.setFicheiro(Constantes.FOTO_DEFAULT);

          GrlPessoa pes = new GrlPessoa();
          pes.setFkIdFoto(foto);
          pes.setFkIdEstadoCivil(new GrlEstadoCivil());
          pes.setFkIdEndereco(new GrlEndereco());
          pes.getFkIdEndereco().setFkIdComuna(new GrlComuna());
          pes.setFkIdContacto(new GrlContacto());
          pes.setFkIdSexo(new GrlSexo());
          pes.setFkIdReligiao(new GrlReligiao());
          pes.setFkIdNacionalidade(new GrlPais());

          return pes;
     }

     public GrlPessoa getPessoa ()
     {
          if (pessoa == null)
          {
               pessoa = getInstanciaPessoa();
               pessoa.setGrlDocumentoIdentificacaoList(new ArrayList<GrlDocumentoIdentificacao>());
          }
          return pessoa;
     }

     public void setPessoa (GrlPessoa pessoa)
     {
          this.pessoa = pessoa;
          
          if (pessoa != null)
          {
               //Fazendo set de algumas propriedades da ItensAjaxBean para aparecerem selecionados nas combobox da dialog editar
               GrlComuna c = pessoa.getFkIdEndereco().getFkIdComuna();

               GrlComuna com = comunaFacade.find(c.getPkIdComuna());
               
               ItensAjaxBean itensAjaxBean = obterItensAjaxBean();
               
               itensAjaxBean.setPais(com.getFkIdMunicipio().getFkIdProvincia().getFkIdPais().getPkIdPais());
               itensAjaxBean.setProvincia(com.getFkIdMunicipio().getFkIdProvincia().getPkIdProvincia());
               itensAjaxBean.setMunicipio(com.getFkIdMunicipio().getPkIdMunicipio());
          }
          
     }

     public GrlPessoa getPessoaPesquisa ()
     {
          if (pessoaPesquisa == null)
          {
               pessoaPesquisa = getInstanciaPessoa();
               pessoaPesquisa.setGrlDocumentoIdentificacaoList(new ArrayList<GrlDocumentoIdentificacao>());
               pessoaPesquisa.getGrlDocumentoIdentificacaoList().add(0, new GrlDocumentoIdentificacao());
               pessoaPesquisa.getGrlDocumentoIdentificacaoList().get(0).setFkTipoDocumentoIdentificacao(new GrlTipoDocumentoIdentificacao());
          }
          return pessoaPesquisa;
     }

     public void setPessoaPesquisa (GrlPessoa pessoaPesquisa)
     {
          this.pessoaPesquisa = pessoaPesquisa;
     }

     public GrlPessoa getPessoaVisualizar ()
     {
          return pessoaVisualizar;
     }

     public void setPessoaVisualizar (GrlPessoa pessoaVisualizar)
     {
          this.pessoaVisualizar = pessoaVisualizar;
     }

     public boolean getPesquisar ()
     {
          return pesquisar;
     }

     public void setPesquisar (boolean pesquisar)
     {
          this.pesquisar = pesquisar;
     }

     public int getTipoDocumento ()
     {
          return tipoDocumento;
     }

     public void setTipoDocumento (int tipoDocumento)
     {
          this.tipoDocumento = tipoDocumento;
     }

     public String getNumeroDocumento ()
     {
          return numeroDocumento;
     }

     public void setNumeroDocumento (String numeroDocumento)
     {
          this.numeroDocumento = numeroDocumento;
     }

     public Part getFotoCarregada ()
     {
          return fotoCarregada;
     }

     public void setFotoCarregada (Part fotoCarregada)
     {
          this.fotoCarregada = fotoCarregada;
     }

     public String apresentarFoto ()
     {
          String foto = getPessoa().getFkIdFoto().getFicheiro();
          return (Constantes.PASTA_FOTO + foto).trim();
     }

     public String previsualizarFoto ()
     {
          String foto = getPessoa().getFkIdFoto().getFicheiro();
          return (Constantes.PASTA_FOTO + foto + "?faces-redirect=true").trim();
     }

     public String getPastaFoto ()
     {
          return Constantes.PASTA_FOTO;
     }

     public void uploadFoto ()
     {
          try
          {
               UploadFicheiro upload = UploadFicheiro.getInstance();
               String novoNome = upload.gravar(fotoCarregada, Constantes.DESTINO_FOTO, "FOTO");
               removerFoto(getPessoa().getFkIdFoto(), false);
               getPessoa().getFkIdFoto().setFicheiro(novoNome);
               fotoCarregada = null;
               System.out.println("Foto carregada com sucesso !");
          }
          catch (IOException e)
          {
               e.printStackTrace();
               Mensagem.erroMsg(e.getMessage());
          }
     }

     /**
      * Remove uma determinada foto(ficheiro) que foi carregada no servidor
      *
      * @param foto Entidade que contém o nome do ficheiro (foto)
      * @param apresentarMensagem flag booleana que indica se o resultado da
      * operação será apresentado
      */
     public void removerFoto (GrlFicheiroAnexado foto, boolean apresentarMensagem)
     {
          boolean apagou = false;

          if (!foto.getFicheiro().equals(Constantes.FOTO_DEFAULT))
          {
               apagou = UploadFicheiro.getInstance().apagarFicheiro(new java.io.File(Constantes.DESTINO_FOTO + foto.getFicheiro()));
          }

          if (apresentarMensagem)
          {
               if (apagou)
               {
                    foto.setFicheiro(Constantes.FOTO_DEFAULT);
                    Mensagem.sucessoMsg("Foto removida com sucesso !");
               }
               else
               {
                    Mensagem.erroMsg("Não foi possível remover a foto !");
               }
          }
     }

     public void createPessoaSemTransaccao () throws Exception
     {
          ficheiroAnexadoFacade.create(pessoa.getFkIdFoto());
          contactoFacade.create(pessoa.getFkIdContacto());
          enderecoFacade.create(pessoa.getFkIdEndereco());
          if (pessoa.getFkIdReligiao().getPkIdReligiao() == null)
          {
               pessoa.setFkIdReligiao(null);
          }
          pessoaFacade.create(pessoa);
          adicionarDocumentosIdentificacao();
          Mensagem.sucessoMsg("Pessoa gravada com sucesso! "+pessoa.getPkIdPessoa());
     }

     public void editPessoaSemTransaccao () throws Exception
     {
          int idNacionalidade = pessoa.getFkIdNacionalidade().getPkIdPais();
          int idSexo = pessoa.getFkIdSexo().getPkIdSexo();
          int idComuna = pessoa.getFkIdEndereco().getFkIdComuna().getPkIdComuna();

          ficheiroAnexadoFacade.edit(pessoa.getFkIdFoto());
          contactoFacade.edit(pessoa.getFkIdContacto());
          pessoa.setFkIdNacionalidade(new GrlPais(idNacionalidade));
          pessoa.setFkIdSexo(new GrlSexo(idSexo));
          pessoa.getFkIdEndereco().setFkIdComuna(new GrlComuna(idComuna));
          enderecoFacade.edit(pessoa.getFkIdEndereco());
          pessoaFacade.edit(pessoa);

          Mensagem.sucessoMsg("Pessoa editada com sucesso!");
     }

     public String create ()
     {
          try
          {
               userTransaction.begin();

               ficheiroAnexadoFacade.create(pessoa.getFkIdFoto());
               contactoFacade.create(pessoa.getFkIdContacto());
               enderecoFacade.create(pessoa.getFkIdEndereco());
               if (pessoa.getFkIdReligiao().getPkIdReligiao() == null)
               {
                    pessoa.setFkIdReligiao(null);
               }
               pessoaFacade.create(pessoa);
               adicionarDocumentosIdentificacao();

               userTransaction.commit();

               Mensagem.sucessoMsg("Pessoa gravada com sucesso!");

               pessoa = null;

          }
          catch (Exception e)
          {
               try
               {
                    userTransaction.rollback();
                    Mensagem.erroMsg(e.toString());
               }
               catch (IllegalStateException | SecurityException | SystemException ex)
               {
                    Mensagem.erroMsg("Rollback: " + ex.toString());
               }
          }

          return null;
     }

     public void adicionarDocumentosIdentificacao () throws Exception
     {
          for (GrlDocumentoIdentificacao doc : pessoa.getGrlDocumentoIdentificacaoList())
          {
               doc.setFkIdPessoa(pessoa);
               documentoIdentificacaoFacade.create(doc);
          }
     }

     public String edit ()
     {
          try
          {
               userTransaction.begin();
               //Se a pessoa já existe
               if (pessoa.getPkIdPessoa() == null)
               {
                    throw new NullPointerException("Id pessoa null");
               }

               int idNacionalidade = pessoa.getFkIdNacionalidade().getPkIdPais();
               int idSexo = pessoa.getFkIdSexo().getPkIdSexo();
               int idComuna = pessoa.getFkIdEndereco().getFkIdComuna().getPkIdComuna();

               ficheiroAnexadoFacade.edit(pessoa.getFkIdFoto());
               contactoFacade.edit(pessoa.getFkIdContacto());
               pessoa.setFkIdNacionalidade(new GrlPais(idNacionalidade));
               pessoa.setFkIdSexo(new GrlSexo(idSexo));
               pessoa.getFkIdEndereco().setFkIdComuna(new GrlComuna(idComuna));
               enderecoFacade.edit(pessoa.getFkIdEndereco());
               pessoaFacade.edit(pessoa);

               Mensagem.sucessoMsg("Pessoa editada com sucesso!");

               userTransaction.commit();
               limparPesquisa();

               return "pessoaPesquisarGrl.xhtml?faces-redirect=true";
          }
          catch (Exception e)
          {
               try
               {
                    userTransaction.rollback();
                    Mensagem.erroMsg(e.toString());
               }
               catch (IllegalStateException | SecurityException | SystemException ex)
               {
                    Mensagem.erroMsg("Rollback: " + ex.toString());
               }
          }

          return null;
     }

     public String novaPessoa ()
     {
          return "pessoaNovaEditarGrl.xhtml?faces-redirect=true";
     }

     public List<GrlPessoa> pesquisarPessoas ()
     {
          if (pesquisar)
          {
               List<GrlPessoa> pp;
               pp = pessoaFacade.findPessoa(pessoaPesquisa, true, true, true, true, true);
               if (pp == null || pp.isEmpty())
               {
                    Mensagem.erroMsg("Nenhum registo encontrado para esta pesquisa");
               }

               return pp;
          }

          return null;
     }

     public String voltar ()
     {
          limparPesquisa();
          pessoa = getInstanciaPessoa();
          return "pessoaPesquisarGrl.xhtml?faces-redirect=true";
     }

     public String limparPesquisa ()
     {
          pesquisar = false;
          pessoaPesquisa = pessoaVisualizar = null;

          return "pessoaPesquisarGrl.xhtml?faces-redirect=true";
     }

     /**
      * @author Ornela F. Boaventura
      *
      * Criado para atender algumas necessidades de pesquisa de pessoas em
      * recursos humanos
      *
      * @return
      */
     public String limparPesquisaRH ()
     {
          pesquisar = false;
          pessoaPesquisa = pessoaVisualizar = null;

          return "pessoaPesquisarGrl.xhtml?faces-redirect=true";
     }

     /**
      * @author Ornela F. Boaventura
      * @param pessoa
      * @return
      */
     public boolean isFuncionario (GrlPessoa pessoa)
     {
          return pessoaFacade.isFuncionario(pessoa);
     }

     /**
      * @author Ornela F. Boaventura
      * @param pessoa
      * @return
      */
     public boolean isCandidato (GrlPessoa pessoa)
     {
          return pessoaFacade.isCandidato(pessoa);
     }

     /**
      * @author Ornela F. Boaventura
      * @param pessoa
      * @return
      */
     public boolean isEstagiario (GrlPessoa pessoa)
     {
          return pessoaFacade.isEstagiario(pessoa);
     }

     public List<GrlPessoa> pesquisarPessoasNaoPacientes ()
     {
          if (pesquisar)
          {
               List<GrlPessoa> pp;
               pp = pessoaFacade.findPessoa(pessoaPesquisa, false, true, true, true, true);
               return pp;
          }

          return null;
     }

     public void adicionarDocumentoDeIdentificacao ()
     {
          GrlTipoDocumentoIdentificacao tipoDocumentoObj = grlTipoDocumentoIdentificacaoFacade.find(tipoDocumento);
          if (validarDocumentoIdentificacao(tipoDocumentoObj))
          {
               GrlDocumentoIdentificacao documentoIdentificacao = new GrlDocumentoIdentificacao();
               documentoIdentificacao.setNumeroDocumento(numeroDocumento);
               documentoIdentificacao.setFkTipoDocumentoIdentificacao(tipoDocumentoObj);
               pessoa.getGrlDocumentoIdentificacaoList().add(documentoIdentificacao);
          }
     }

     private boolean validarDocumentoIdentificacao (GrlTipoDocumentoIdentificacao tipoDocumentoObj)
     {
          if (!numeroDocumento.trim().equals(""))
          {
               for (int i = 0; i < pessoa.getGrlDocumentoIdentificacaoList().size(); i++)
               {
                    if (pessoa.getGrlDocumentoIdentificacaoList().get(i).getFkTipoDocumentoIdentificacao().getPkTipoDocumentoIdentificacao()
                        == tipoDocumentoObj.getPkTipoDocumentoIdentificacao())
                    {
                         Mensagem.erroMsg("Tipo de documento já adicionado, só pode ser adicionado uma vez");
                         return false;
                    }
               }
          }
          else
          {
               Mensagem.erroMsg("O número do documento não pode ser um texto em branco");
               return false;
          }
          return true;
     }

     public void removerDocumentoIdentificacao (GrlDocumentoIdentificacao documento)
     {
          for (int i = 0; i < pessoa.getGrlDocumentoIdentificacaoList().size(); i++)
          {
               if (pessoa.getGrlDocumentoIdentificacaoList().get(i).getNumeroDocumento().equalsIgnoreCase(documento.getNumeroDocumento())
                   && pessoa.getGrlDocumentoIdentificacaoList().get(i).getFkTipoDocumentoIdentificacao().getPkTipoDocumentoIdentificacao()
                      == documento.getFkTipoDocumentoIdentificacao().getPkTipoDocumentoIdentificacao())
               {
                    pessoa.getGrlDocumentoIdentificacaoList().remove(i);
                    break;
               }
          }
     }

     private ItensAjaxBean obterItensAjaxBean()
     {
          FacesContext c = FacesContext.getCurrentInstance();
          return (ItensAjaxBean) c.getELContext().getELResolver()
             .getValue(c.getELContext(), null, "itensAjaxBean");
     }
}
