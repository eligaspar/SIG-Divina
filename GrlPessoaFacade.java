/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessao;

import entidade.GrlPessoa;
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
public class GrlPessoaFacade extends AbstractFacade<GrlPessoa>
{

    @PersistenceContext(unitName = "SIG_HDP_Master-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager ()
    {
        return em;
    }

    public GrlPessoaFacade ()
    {
        super(GrlPessoa.class);
    }

    public List<GrlPessoa> pesquisaPorNome (String nome)
    {
        TypedQuery<GrlPessoa> t = this.em.createQuery("SELECT p FROM GrlPessoa p WHERE p.nomeCompleto LIKE :nome", GrlPessoa.class).setParameter("nome", nome + "%");

        List<GrlPessoa> resultado = t.getResultList();

        return resultado;
    }
}
