/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean.rhbean;

import entidade.GrlCentroHospitalar;
import entidade.GrlComuna;
import entidade.GrlEspecialidade;
import entidade.GrlFicheiroAnexado;
import entidade.RhCategoriaProfissao;
import entidade.RhDepartamento;
import entidade.RhEstadoFuncionario;
import entidade.RhFuncionario;
import entidade.RhFuncionarioHasRhSubsidio;
import entidade.RhNivelAcademico;
import entidade.RhSeccaoTrabalho;
import entidade.RhSubsidio;
import entidade.RhTipoFuncionario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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
import sessao.RhFuncionarioFacade;
import util.ItensAjaxBean;
import util.Mensagem;
import managedBean.grlbean.PessoaBean;
import sessao.GrlCentroHospitalarFacade;
import sessao.GrlFicheiroAnexadoFacade;
import sessao.RhFuncionarioHasRhSubsidioFacade;
import sessao.RhSubsidioFacade;
import util.Constantes;
import util.UploadFicheiro;

/**
 *
 * @author Ornela F. Boaventura
 */
@ManagedBean
@SessionScoped
public class RhFuncionarioBean
{

     @Resource
     private UserTransaction userTransaction;

     @EJB
     private GrlFicheiroAnexadoFacade ficheiroAnexadoFacade;
     @EJB
     private GrlCentroHospitalarFacade centroHospitalarFacade;
     @EJB
     private RhFuncionarioFacade funcionarioFacade;
     @EJB
     private RhSubsidioFacade subsidioFacade;
     @EJB
     private RhFuncionarioHasRhSubsidioFacade funcionarioHasRhSubsidioFacade;

     /**
      * Entidades
      */
     private RhFuncionario funcionario, funcionarioPesquisa, funcionarioVisualizar;
     //Entidade que pega os ids da especialidades selecionadas
     private Part anexoCarregado;

     private List<RhFuncionario> funcionariosPesquisados;
     private List<Object> listaSubsidios;

     /**
      * Creates a new instance of funcionarioBean
      */
     public RhFuncionarioBean ()
     {
     }

     public static RhFuncionario getInstanciaFuncionario ()
     {
          RhFuncionario funcionario = new RhFuncionario();
          funcionario.setFkIdPessoa(new PessoaBean().getInstanciaPessoa());
          funcionario.setFkIdAnexoGuiaTransferencia(new GrlFicheiroAnexado());
          funcionario.setFkIdEstadoFuncionario(new RhEstadoFuncionario());
          funcionario.setFkIdTipoFuncionario(new RhTipoFuncionario());
          funcionario.setFkIdNivelAcademico(new RhNivelAcademico());
          funcionario.setFkIdSeccaoTrabalho(new RhSeccaoTrabalho());
          funcionario.setFkIdCategoria(new RhCategoriaProfissao());
          funcionario.setFkIdEspecialidade1(new GrlEspecialidade());
          funcionario.setFkIdEspecialidade2(new GrlEspecialidade());
          funcionario.setFkIdCentroHospitalar(new GrlCentroHospitalar());

          return funcionario;
     }

     public RhFuncionario getFuncionario ()
     {
          if (funcionario == null)
          {
               funcionario = getInstanciaFuncionario();
          }

          return funcionario;
     }

     public void setFuncionario (RhFuncionario funcionario)
     {
          this.funcionario = funcionario;
     }

     public RhFuncionario getFuncionarioPesquisa ()
     {
          if (funcionarioPesquisa == null)
          {
               funcionarioPesquisa = getInstanciaFuncionario();
          }
          return funcionarioPesquisa;
     }

     public void setFuncionarioPesquisa (RhFuncionario funcionarioPesquisa)
     {
          this.funcionarioPesquisa = funcionarioPesquisa;
     }

     public RhFuncionario getFuncionarioVisualizar ()
     {
          return funcionarioVisualizar;
     }

     public void setFuncionarioVisualizar (RhFuncionario funcionarioVisualizar)
     {
          this.funcionarioVisualizar = funcionarioVisualizar;
     }

     public Part getAnexoCarregado ()
     {
          return anexoCarregado;
     }

     public void setAnexoCarregado (Part anexoCarregado)
     {
          this.anexoCarregado = anexoCarregado;
     }

     public List<RhFuncionario> getFuncionariosPesquisados ()
     {
          return funcionariosPesquisados;
     }

     public List<Object> getListaSubsidios ()
     {
          return listaSubsidios;
     }

     public void setListaSubsidios (List<Object> listaSubsidios)
     {
          this.listaSubsidios = listaSubsidios;
     }

     public boolean renderCentDepartSeccaoFuncionarioVisual ()
     {
          if(funcionarioVisualizar != null)
          {
               if (funcionarioVisualizar.getFkIdCentroHospitalar().getFkIdInstituicao()
                   .getDescricao().equalsIgnoreCase("Hospital Divina Providência"))
                    return true;
          }
          return false;
     }
     
     public void pesquisarFuncionarios ()
     {
          funcionariosPesquisados = funcionarioFacade.findFuncionario(funcionarioPesquisa);

          if (funcionariosPesquisados.isEmpty())
          {
               Mensagem.erroMsg("Nenhum registro encontrado para esta pesquisa");
          }
     }

     public String limpar ()
     {
          funcionario = null;

          return "funcionarioNovoRh.xhtml?faces-redirect=true";
     }

     public String limparPesquisa ()
     {
          funcionariosPesquisados = null;
          funcionarioPesquisa = funcionarioVisualizar = null;

          return "funcionarioPesquisarRh.xhtml?faces-redirect=true";
     }

     public String voltar ()
     {
          limpar();

          return limparPesquisa();
     }

     public String create ()
     {
          try
          {
               setFuncionario(funcionario);
               userTransaction.begin();

               PessoaBean pessoaBean = obterPessoaBean();

               if (pessoaBean.getPessoa().getPkIdPessoa() == null)
               {
                    pessoaBean.createPessoaSemTransaccao();
               }
               else
               {
                    pessoaBean.editPessoaSemTransaccao();
               }

               funcionario.setFkIdPessoa(pessoaBean.getPessoa());
//               funcionario.setCodigoFuncionario("F" + funcionario.getFkIdPessoa().getPkIdPessoa());

               
               if(funcionario.getFkIdCentroHospitalar().getPkIdCentro() == null)
                    throw new Exception("Indique o centro hospitalar");
               
               GrlCentroHospitalar centro = centroHospitalarFacade.find(funcionario.getFkIdCentroHospitalar().getPkIdCentro());
               if (! centro.getFkIdInstituicao().getDescricao().equalsIgnoreCase("Hospital Divina Providência"))
                    funcionario.setFkIdSeccaoTrabalho(null);

               Integer especialidade1 = funcionario.getFkIdEspecialidade1().getPkIdEspecialidade();
               Integer especialidade2 = funcionario.getFkIdEspecialidade2().getPkIdEspecialidade();
               
               //Se uma das especialidades não estiver nula
               //Verifica se são iguais
               if(especialidade1 != null || especialidade2 != null)
                    if(especialidade1 == especialidade2)
                         throw new Exception("As especialidades não devem ser iguais");
               
               
               if (especialidade1 == null) {
                    if(especialidade2 != null)
                         throw new Exception("A Especialidade1 deve ser selecionada se possuir apenas uma especialidade e não a Especialidade2");
                    funcionario.setFkIdEspecialidade1(null);
               }
               if (especialidade2 == null)
                    funcionario.setFkIdEspecialidade2(null);

               if (funcionario.getDataAdmissao().after(Calendar.getInstance().getTime()))
                    throw new Exception("A data de admissão não pode ser superior a data de hoje");
               if (funcionario.getFkIdTipoFuncionario().getPkIdTipoFuncionario() == null)
                    throw new Exception("Indique o tipo de funcionário");

               funcionario.setDataCadastro(Calendar.getInstance().getTime());
               
               ficheiroAnexadoFacade.create(funcionario.getFkIdAnexoGuiaTransferencia());
               funcionarioFacade.create(funcionario);

               /**
                * Gravando os subsídios no contrato
                */
               for (Object idSubsidio : listaSubsidios)
               {
                    RhFuncionarioHasRhSubsidio funcHasSub = new RhFuncionarioHasRhSubsidio();
                    funcHasSub.setFkIdFuncionario(funcionario);
                    int idSub = Integer.parseInt("" + idSubsidio);
                    funcHasSub.setFkIdSubsidio(new RhSubsidio(idSub));

                    funcionarioHasRhSubsidioFacade.create(funcHasSub);
               }

               userTransaction.commit();

               Mensagem.sucessoMsg("Funcionário guardado com sucesso!");
               pessoaBean.setPessoa(null);
               limpar();
          }
          catch (Exception e)
          {
               try
               {
                    e.printStackTrace();
                    Mensagem.erroMsg(e.toString());
                    userTransaction.rollback();
               }
               catch (IllegalStateException | SecurityException | SystemException ex)
               {
                    e.printStackTrace();
                    Mensagem.erroMsg("Rollback: " + ex.toString());
               }
          }

          return null;
     }

     public String edit ()
     {
          Mensagem.sucessoMsg("Funcionário editado com sucesso!");

          return null;
     }

     public String remove (RhFuncionario funcionarioRemover)
     {
          try
          {
               userTransaction.begin();

               funcionarioFacade.remove(funcionarioRemover);
               ficheiroAnexadoFacade.remove(funcionarioRemover.getFkIdAnexoGuiaTransferencia());
               removerAnexo(funcionarioRemover.getFkIdAnexoGuiaTransferencia(), false);

               userTransaction.commit();

               Mensagem.sucessoMsg("Funcionário removido com sucesso!");
               pesquisarFuncionarios();
          }
          catch (Exception e)
          {
               try
               {
                    e.printStackTrace();
                    Mensagem.erroMsg("Este funcionário possui registro de actividades, impossível remover !");
                    Mensagem.erroMsg(e.toString());
                    userTransaction.rollback();
               }
               catch (IllegalStateException | SecurityException | SystemException ex)
               {
                    e.printStackTrace();
                    Mensagem.erroMsg("Rollback: " + ex.toString());
               }
          }

          return null;
     }

     public String previsualizarAnexo (GrlFicheiroAnexado anexo)
     {
          if (anexo.getFicheiro() != null && !anexo.getFicheiro().trim().isEmpty())
          {
               return (Constantes.PASTA_ANEXO_FUNCIONARIO + anexo.getFicheiro() + "?faces-redirect=true").trim();
          }

          return null;
     }

     public void uploadAnexo ()
     {
          try
          {
               UploadFicheiro upload = UploadFicheiro.getInstance();

               String novoNome = upload.gravar(anexoCarregado, Constantes.DESTINO_ANEXO_FUNCIONARIO, "GUIA_TRANSFER");

               removerAnexo(funcionario.getFkIdAnexoGuiaTransferencia(), false);
               funcionario.getFkIdAnexoGuiaTransferencia().setFicheiro(novoNome);

               anexoCarregado = null;

               System.out.println("Anexo carregado com sucesso !");
          }
          catch (IOException e)
          {
               e.printStackTrace();
               Mensagem.erroMsg(e.getMessage());
          }
     }

     /**
      * Remove um determinado anexo(ficheiro) que foi carregado no servidor
      *
      * @param anexo Entidade que contém o nome do ficheiro
      * @param apresentarMensagem flag booleana que indica se o resultado da
      * operação será apresentado
      */
     public void removerAnexo (GrlFicheiroAnexado anexo, boolean apresentarMensagem)
     {
          boolean b = UploadFicheiro.getInstance().apagarFicheiro(new java.io.File(Constantes.DESTINO_ANEXO_FUNCIONARIO + anexo.getFicheiro()));

          anexo.setFicheiro(null);

          if (apresentarMensagem)
          {
               if (b)
               {
                    Mensagem.sucessoMsg("Anexo removido");
               }
               else
               {
                    Mensagem.erroMsg("Não foi possível remover o anexo");
               }
          }
     }

     private void testarConsistenciaSubsidios () throws Exception
     {
          String resultado = "";

          if (listaSubsidios == null)
          {
               listaSubsidios = new ArrayList<>();
          }

          for (Object s : listaSubsidios)
          {
               System.out.println("Subsidio: " + s);
          }

          for (RhSubsidio sub : subsidioFacade.findAll())
          {
               if (sub.getObrigatorio() && !listaSubsidios.contains("" + sub.getPkIdSubsidio()))
               {
                    resultado += sub.getDescricaoSubsidio() + ", ";
               }
          }

          if (!resultado.trim().isEmpty())
          {
               throw new Exception("Os seguintes subsídios são obrigatórios e deve seleciona-los: " + resultado);
          }
     }

     private PessoaBean obterPessoaBean ()
     {
          FacesContext c = FacesContext.getCurrentInstance();
          return (PessoaBean) c.getELContext().getELResolver()
                  .getValue(c.getELContext(), null, "pessoaBean");
     }

     private ItensAjaxBean obterItensAjaxBean ()
     {
          FacesContext c = FacesContext.getCurrentInstance();
          return (ItensAjaxBean) c.getELContext().getELResolver()
                  .getValue(c.getELContext(), null, "itensAjaxBean");
     }

}
