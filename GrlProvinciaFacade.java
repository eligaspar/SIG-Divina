/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessao;

import entidade.GrlProvincia;
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
public class GrlProvinciaFacade extends AbstractFacade<GrlProvincia>
{

    @PersistenceContext(unitName = "SIG_HDP_Master-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager ()
    {
        return em;
    }

    public GrlProvinciaFacade ()
    {
        super(GrlProvincia.class);
    }

    public List<GrlProvincia> pesquisaPorPais (Integer idPais)
    {
        TypedQuery<GrlProvincia> t = this.em.createQuery("SELECT p FROM GrlProvincia p WHERE p.fkIdPais.pkIdPais = :idPais", GrlProvincia.class).setParameter("idPais", idPais);

        List<GrlProvincia> resultado = t.getResultList();

        return resultado;
    }
}
