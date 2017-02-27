/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessao;

import entidade.RhFuncionario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Ornela F. Boaventura
 */
@Stateless
public class RhFuncionarioFacade extends AbstractFacade<RhFuncionario>
{

     @PersistenceContext(unitName = "SIG_HDP_Master-ejbPU")
     private EntityManager em;

     @Override
     protected EntityManager getEntityManager ()
     {
          return em;
     }

     public RhFuncionarioFacade ()
     {
          super(RhFuncionario.class);
     }

     public List<RhFuncionario> listaDosMedicos ()
     {
          TypedQuery<RhFuncionario> query = em.createQuery("SELECT medico FROM RhFuncionario medico WHERE medico.fkIdCategoria.fkIdProfissao.descricao ='Médico'", RhFuncionario.class);

          return query.getResultList();
     }

     public List<RhFuncionario> findFuncionariosComContrato ()
     {
          TypedQuery<RhFuncionario> query = em.createQuery("SELECT f FROM RhFuncionario f WHERE f.fkIdContrato <> NULL", RhFuncionario.class);

          return query.getResultList();
     }

     public List<RhFuncionario> findFuncionariosPorSeccao (Integer idSeccao)
     {
          TypedQuery<RhFuncionario> query = em.createQuery("SELECT f FROM RhFuncionario f WHERE f.fkIdSeccaoTrabalho.pkIdSeccaoTrabalho = :idSeccao", RhFuncionario.class)
                  .setParameter("idSeccao", idSeccao);

          return query.getResultList();
     }

     public List<RhFuncionario> findFuncionariosPorAssiduidadeMensal (Integer idAssiduidade)
     {
          TypedQuery<RhFuncionario> query = em.createQuery("SELECT DISTINCT f FROM RhFuncionario f, RhFuncionarioHasRhTipoPresenca funHas "
                  + "WHERE f.pkIdFuncionario = funHas.fkIdFuncionario.pkIdFuncionario AND funHas.fkIdAssiduidadeMensal.pkIdAssiduidadeMensal = :idAssiduidade ORDER BY f.fkIdPessoa.nome", RhFuncionario.class)
                  .setParameter("idAssiduidade", idAssiduidade);

          return query.getResultList();
     }

     public List<RhFuncionario> findFuncionario (RhFuncionario funcionario)
     {
          String query = constroiQuery(funcionario);

          TypedQuery<RhFuncionario> t = em.createQuery(query, RhFuncionario.class);

          if (funcionario.getFkIdEstadoFuncionario().getPkIdEstadoFuncionario() != null)
          {
               t.setParameter("estadoFuncionario", funcionario.getFkIdEstadoFuncionario().getPkIdEstadoFuncionario());
          }

          if (funcionario.getFkIdTipoFuncionario().getPkIdTipoFuncionario() != null)
          {
               t.setParameter("tipoFuncionario", funcionario.getFkIdTipoFuncionario().getPkIdTipoFuncionario());
          }

          if (funcionario.getFkIdCentroHospitalar().getPkIdCentro() != null)
          {
               t.setParameter("centro", funcionario.getFkIdCentroHospitalar().getPkIdCentro());
          }

          if (funcionario.getFkIdPessoa().getNome() != null && !funcionario.getFkIdPessoa().getNome().trim().isEmpty())
          {
               t.setParameter("nome", funcionario.getFkIdPessoa().getNome() + "%");
          }

          if (funcionario.getFkIdPessoa().getNomeDoMeio() != null && !funcionario.getFkIdPessoa().getNomeDoMeio().trim().isEmpty())
          {
               t.setParameter("nomeDoMeio", funcionario.getFkIdPessoa().getNomeDoMeio() + "%");
          }

          if (funcionario.getFkIdPessoa().getSobreNome() != null && !funcionario.getFkIdPessoa().getSobreNome().trim().isEmpty())
          {
               t.setParameter("sobreNome", funcionario.getFkIdPessoa().getSobreNome() + "%");
          }

          if (funcionario.getNumeroCartao() != null && !funcionario.getNumeroCartao().trim().isEmpty())
          {
               t.setParameter("numeroCartao", funcionario.getNumeroCartao() + "%");
          }

          if (funcionario.getFkIdPessoa().getFkIdSexo().getPkIdSexo() != null)
          {
               t.setParameter("sexo", funcionario.getFkIdPessoa().getFkIdSexo().getPkIdSexo());
          }

          List<RhFuncionario> resultado = t.getResultList();

          return resultado;
     }

     public String constroiQuery (RhFuncionario funcionario)
     {
          String query = "SELECT f FROM RhFuncionario f WHERE f.pkIdFuncionario = f.pkIdFuncionario";

          if (funcionario.getFkIdEstadoFuncionario().getPkIdEstadoFuncionario() != null)
          {
               query += " AND f.fkIdEstadoFuncionario.pkIdEstadoFuncionario = :estadoFuncionario";
          }

          if (funcionario.getFkIdTipoFuncionario().getPkIdTipoFuncionario() != null)
          {
               query += " AND f.fkIdTipoFuncionario.pkIdTipoFuncionario = :tipoFuncionario";
          }

          if (funcionario.getFkIdCentroHospitalar().getPkIdCentro() != null)
          {
               query += " AND f.fkIdCentroHospitalar.pkIdCentro = :centro";
          }

          if (funcionario.getFkIdPessoa().getNome() != null && !funcionario.getFkIdPessoa().getNome().trim().isEmpty())
          {
               query += " AND f.fkIdPessoa.nome LIKE :nome";
          }

          if (funcionario.getFkIdPessoa().getNomeDoMeio() != null && !funcionario.getFkIdPessoa().getNomeDoMeio().trim().isEmpty())
          {
               query += " AND f.fkIdPessoa.nomeDoMeio LIKE :nomeDoMeio";
          }

          if (funcionario.getFkIdPessoa().getSobreNome() != null && !funcionario.getFkIdPessoa().getSobreNome().trim().isEmpty())
          {
               query += " AND f.fkIdPessoa.sobreNome LIKE :sobreNome";
          }

          if (funcionario.getNumeroCartao() != null && !funcionario.getNumeroCartao().trim().isEmpty())
          {
               query += " AND f.numeroCartao LIKE :numeroCartao";
          }

          if (funcionario.getFkIdPessoa().getFkIdSexo().getPkIdSexo() != null)
          {
               query += " AND f.fkIdPessoa.fkIdSexo.pkIdSexo = :sexo";
          }

          query += " ORDER BY f.fkIdPessoa.nome, f.fkIdPessoa.nomeDoMeio, f.fkIdPessoa.sobreNome";
          
          return query;
     }

}
