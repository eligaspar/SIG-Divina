/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessao;

import entidade.GrlEspecialidade;
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
public class GrlEspecialidadeFacade extends AbstractFacade<GrlEspecialidade>
{

    @PersistenceContext(unitName = "SIG_HDP_Master-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager ()
    {
        return em;
    }

    public GrlEspecialidadeFacade ()
    {
        super(GrlEspecialidade.class);
    }

    public List<GrlEspecialidade> pesquisaPorProfissao (Integer idProfissao)
    {
        TypedQuery<GrlEspecialidade> t = this.em.createQuery("SELECT e FROM GrlEspecialidade e WHERE e.fkIdProfissao.pkIdProfissao = :idProfissao", GrlEspecialidade.class).setParameter("idProfissao", idProfissao);

        List<GrlEspecialidade> resultado = t.getResultList();

        return resultado;
    }
}
